import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import React, {useEffect, useState} from "react";
import {listCategoriesByParentId} from "./ProductCategoryService";
import {makeStyles} from "@material-ui/core/styles";
import {bindActionCreators} from "redux";

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
}));


function CategoryList(props) {
    const classes = useStyles();
    const [activeDepth, setActiveDepth] = useState(0);
    const [categoryDepth0, setCategoryDepth0] = useState("");
    const [categoryDepth1, setCategoryDepth1] = useState("");
    const [categoryDepth2, setCategoryDepth2] = useState("");
    const [categoryDepth3, setCategoryDepth3] = useState("");
    const [categoryDepth4, setCategoryDepth4] = useState("");
    const [categoryDepth5, setCategoryDepth5] = useState("");
    const [categoryListDepth0, setCategoryListDepth0] = useState([]);
    const [categoryListDepth1, setCategoryListDepth1] = useState([]);
    const [categoryListDepth2, setCategoryListDepth2] = useState([]);
    const [categoryListDepth3, setCategoryListDepth3] = useState([]);
    const [categoryListDepth4, setCategoryListDepth4] = useState([]);
    const [categoryListDepth5, setCategoryListDepth5] = useState([]);

    const {handleActiveCategoryChange, handleLastDepthChange, active,
        handlePageChange, handleActiveDepthChange, upsertStatus} = props;

    useEffect(() => {
        if (active || upsertStatus) {
            if (upsertStatus) {
                setCategoryDepth0("");
            }
            handleActiveCategoryChange("");
            setCategoryListDepth1([]);
            setCategoryListDepth2([]);
            setCategoryListDepth3([]);
            setCategoryListDepth4([]);
            setCategoryListDepth5([]);
            setActiveDepth(0);
            listCategoriesByParentId("").then(response => {
                setCategoryListDepth0(response.data);
            }).catch(e => {
                console.log(e);
            });
        }
    }, [active, upsertStatus]);

    useEffect(() => {
        handleActiveCategoryChange(categoryDepth5 || categoryDepth4 || categoryDepth3 || categoryDepth2 || categoryDepth1 || categoryDepth0 || "");
    }, [categoryDepth5, categoryDepth4, categoryDepth3, categoryDepth2, categoryDepth1, categoryDepth0]);

    useEffect(() => {
        if(categoryDepth5) {
            handleLastDepthChange(true);
        } else {
            handleLastDepthChange(false)
        }
    }, [categoryDepth5]);

    useEffect(() => {
        handleActiveDepthChange(activeDepth);
    }, [activeDepth]);

    const handleCategoryChange = (e, depth) => {
        const parentId = e.target.value;
        handlePageChange(0);
        switch (depth) {
            case 0:
                setCategoryDepth0(parentId);
                setCategoryDepth1("");
                setCategoryDepth2("");
                setCategoryDepth3("");
                setCategoryDepth4("");
                setCategoryDepth5("");
                break;
            case 1:
                setCategoryDepth1(parentId);
                setCategoryDepth2("");
                setCategoryDepth3("");
                setCategoryDepth4("");
                setCategoryDepth5("");
                break;
            case 2:
                setCategoryDepth2(parentId);
                setCategoryDepth3("");
                setCategoryDepth4("");
                setCategoryDepth5("");
                break;
            case 3:
                setCategoryDepth3(parentId);
                setCategoryDepth4("");
                setCategoryDepth5("");
                break;
            case 4:
                setCategoryDepth4(parentId);
                setCategoryDepth5("");
                break;
            case 5:
                setCategoryDepth5(parentId);
                break;
            default:
            // code block
        }

        setActiveDepth(parentId && depth < 5 ? depth + 1 : depth);

        if (parentId && depth < 6) {

            listCategoriesByParentId(parentId).then(response => {
                const categories = response.data;
                switch (depth) {
                    case 0:
                        setCategoryListDepth1(categories);
                        break;
                    case 1:
                        setCategoryListDepth2(categories);
                        break;
                    case 2:
                        setCategoryListDepth3(categories);
                        break;
                    case 3:
                        setCategoryListDepth4(categories);
                        break;
                    case 4:
                        setCategoryListDepth5(categories);
                        break;
                    case 5:
                        setCategoryDepth5(parentId);
                        break;
                    default:
                    // code block
                }

            })
        }
    }

    return (
        <>
            {(activeDepth === 0 || activeDepth === 1 || activeDepth === 2 ||
                activeDepth === 3 || activeDepth === 4 || activeDepth === 5) &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-0">Kategori 1</InputLabel>
                <Select
                    native
                    value={categoryDepth0}
                    placeholder={"Kategori Ekle"}
                    onChange={(e) => handleCategoryChange(e, 0)}
                    inputProps={{
                        name: 'categoryDepth0',
                        id: 'category-depth-0',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth0.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }
            {(activeDepth === 1 || activeDepth === 2 ||
                activeDepth === 3 || activeDepth === 4 || activeDepth === 5) &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-1">Kategori 2</InputLabel>
                <Select
                    native
                    value={categoryDepth1}
                    onChange={(e) => handleCategoryChange(e, 1)}
                    inputProps={{
                        name: 'categoryDepth1',
                        id: 'category-depth-1',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth1.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }
            {(activeDepth === 2 ||
                activeDepth === 3 || activeDepth === 4 || activeDepth === 5) &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-2">Kategori 3</InputLabel>
                <Select
                    native
                    value={categoryDepth2}
                    onChange={(e) => handleCategoryChange(e, 2)}
                    inputProps={{
                        name: 'categoryDepth2',
                        id: 'category-depth-2',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth2.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }
            {(activeDepth === 3 || activeDepth === 4 || activeDepth === 5) &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-3">Kategori 4</InputLabel>
                <Select
                    native
                    value={categoryDepth3}
                    onChange={(e) => handleCategoryChange(e, 3)}
                    inputProps={{
                        name: 'categoryDepth3',
                        id: 'category-depth-3',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth3.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }
            {(activeDepth === 4 || activeDepth === 5) &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-4">Kategori 5</InputLabel>
                <Select
                    native
                    value={categoryDepth4}
                    onChange={(e) => handleCategoryChange(e, 4)}
                    inputProps={{
                        name: 'categoryDepth4',
                        id: 'category-depth-4',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth4.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }
            {activeDepth === 5 &&
            <FormControl fullWidth className={classes.formControl}>
                <InputLabel htmlFor="category-depth-5">Kategori 6</InputLabel>
                <Select
                    native
                    value={categoryDepth5}
                    onChange={(e) => handleCategoryChange(e, 5)}
                    inputProps={{
                        name: 'categoryDepth5',
                        id: 'category-depth-5',
                    }}
                >
                    <option aria-label="None" value=""/>
                    {categoryListDepth5.map((c, index) => <option key={index} value={c.id}>{c.name}</option>)}
                </Select>
            </FormControl>
            }</>);

}


export default CategoryList;