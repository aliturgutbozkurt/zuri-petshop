import { createSlice } from '@reduxjs/toolkit';

let lastId = 0;

const slice = createSlice({
  name: 'users',
  initialState: {
    list: [],
    loading: false,
    lastFetch: null,
  },
  reducers: {
    userAdded: (users, action) => {
      users.push({
        id: ++lastId,
        name: action.payload.name,
      });
    },
  },
});

export default slice.reducer;
export const { userAdded } = slice.actions;
