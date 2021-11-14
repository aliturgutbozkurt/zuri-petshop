import { DefaultTheme } from "@react-navigation/native";
import colors from "../config/colors.js";

export default {
  ...DefaultTheme,
  textTransform:'capitalize',
  colors: {
    ...DefaultTheme.colors,
    primary: colors.primary,
    background: colors.white,
  },
};
