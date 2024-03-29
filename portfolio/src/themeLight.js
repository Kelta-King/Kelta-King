// theme.js
import { createTheme } from '@mui/material/styles';

// Create a theme instance.
const themeLight = createTheme({
    palette: {
        primary: {
            main: '#2196f3', // Change primary color
        },
        secondary: {
            main: '#f50057', // Change secondary color
        },
    },
});

export default themeLight;
