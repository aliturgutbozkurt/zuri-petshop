import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { addBug, resolveBug, getUnresolvedBugs, loadBugs } from '../bugs';
import configureStore from '../configureStore';

describe('bugsSlice', () => {
  let fakeAxios;
  let store;

  beforeEach(() => {
    fakeAxios = new MockAdapter(axios);
    store = configureStore();
  });

  //Helper functions 😇
  const bugsSlice = () => store.getState().entities.bugs;
  const createState = () => ({
    entities: {
      bugs: {
        list: [],
      },
    },
  });

  describe('loading bugs', () => {
    describe('if the bugs exist in the cache', () => {
      it('the bugs should not be fetched from the server again', async () => {
        //Arrange
        fakeAxios.onGet('/bugs').reply(200, [{ id: 1 }]);

        //Act
        await store.dispatch(loadBugs());
        await store.dispatch(loadBugs());

        //Assert
        expect(fakeAxios.history.get.length).toBe(1); //length of array to equal 1
      });
    });
    describe(`if the bugs don't exist in the cache`, () => {
      it(`the bugs should be fetched from the server and put in the store`, async () => {
        //Arrange
        fakeAxios.onGet('/bugs').reply(200, [{ id: 1 }]);

        //Act
        await store.dispatch(loadBugs());

        //Assert
        expect(bugsSlice().list).toHaveLength(1);
      });

      describe(`loading indicator`, () => {
        it(`should be true while fetching bugs`, () => {
          //Arrange, Act and Assert
          fakeAxios.onGet('/bugs').reply(() => {
            expect(bugsSlice().loading).toBe(true);
            return [200, [{ id: 1 }]];
          });
          store.dispatch(loadBugs());
        });

        it(`should be false after fetching bugs`, async () => {
          //Arrange
          fakeAxios.onGet('/bugs').reply(200, [{ id: 1 }]);

          //Act
          await store.dispatch(loadBugs());

          //Assert
          expect(bugsSlice().loading).toBe(false);
        });

        it(`should be false after server returns an error`, async () => {
          //Arrange
          fakeAxios.onGet('/bugs').reply(500);

          //Act
          await store.dispatch(loadBugs());

          //Assert
          expect(bugsSlice().loading).toBe(false);
        });
      });
    });
  });

  it(`should mark the bug as resolved if it's resolved at the server`, async () => {
    //Arrange
    fakeAxios.onPatch('/bugs/1').reply(200, { id: 1, resolved: true });
    fakeAxios.onPost('/bugs').reply(200, { id: 1 });

    //Act
    await store.dispatch(addBug({}));
    await store.dispatch(resolveBug(1));

    //Assert
    expect(bugsSlice().list[0].resolved).toBe(true);
  });

  it(`should not mark the bug as resolved if it's not resolved at the server`, async () => {
    //Arrange
    fakeAxios.onPatch('/bugs/1').reply(500);
    fakeAxios.onPost('/bugs').reply(200, { id: 1 });

    //Act
    await store.dispatch(addBug({}));
    await store.dispatch(resolveBug(1));

    //Assert
    expect(bugsSlice().list[0].resolved).not.toBe(true);
  });

  it(`should add the bug to the store if it's saved to the server`, async () => {
    //Arrange
    const bug = { description: 'a' };
    const savedBug = { ...bug, id: 1 };
    fakeAxios.onPost('/bugs').reply(200, savedBug);

    //Act
    await store.dispatch(addBug(bug));

    //Assert
    expect(bugsSlice().list).toContainEqual(savedBug);
  });

  it(`should not add the bug to the store if it's not saved to the server`, async () => {
    //Arrange
    const bug = { description: 'a' };
    fakeAxios.onPost('/bugs').reply(500);

    //Act
    await store.dispatch(addBug(bug));

    //Assert
    expect(bugsSlice().list).toHaveLength(0);
  });

  describe('selectors', () => {
    it('getUnresolvedbugs', () => {
      //Arrange
      const state = createState();
      state.entities.bugs.list = [
        { id: 1, resolved: true },
        { id: 2, resolved: false },
        { id: 3, resolved: false },
      ];
      //Act
      const result = getUnresolvedBugs(state);
      //Assert
      expect(result).toHaveLength(2);
    });
  });
});
