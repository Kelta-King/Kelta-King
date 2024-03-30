import { ThemeProvider } from '@mui/material/styles';
import './App.css';
import themeDark from './themeDark';
import themeLight from './themeLight';
import { Box, Button } from '@mui/material';
import { useState } from 'react';
import FormControlLabel from '@mui/material/FormControlLabel';
import Switch from '@mui/material/Switch';

function App() {
	const [theme, setTheme] = useState("Light");

	const handleThemeChange = (event) => {
		if (event.target.checked) {
			setTheme("Dark");
		}
		else {
			setTheme("Light");
		}
	}

	return (
		<div className="App">
			<FormControlLabel
				control={<Switch />}
				label={theme}
				onChange={handleThemeChange}
			/>
			{theme == "Light" ? (
				<ThemeProvider theme={themeLight}>
					<Box style={{ width: "50%", backgroundColor: "black" }}>
						<Button variant='contained'>Clieck</Button>
					</Box>
				</ThemeProvider>
			) : (
				<ThemeProvider theme={themeDark}>
					<Box style={{ width: "50%", backgroundColor: "black" }}>
						<Button variant='contained'>Clieck</Button>
					</Box>
				</ThemeProvider>
			)}


		</div>
	);
}

export default App;
