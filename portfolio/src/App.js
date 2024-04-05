import { ThemeProvider } from '@mui/material/styles';
import './App.css';
import { useEffect, useState } from 'react';
import lightTheme from './ColorTheme/lightTheme';
import darkTheme from './ColorTheme/darkTheme';
import Header from './Common/Header';

function App() {
	const [colorTheme, setcolorTheme] = useState("Dark");

	const switchColorTheme = (event) => {
		console.log("Yo");
		if (event.target.checked) {
			setcolorTheme("Light");
		}
		else {
			setcolorTheme("Dark");
		}
	}
	var theme = colorTheme == "Dark" ? darkTheme : lightTheme;

	return (
		<div
			className="App"
			style={{
				backgroundColor: theme.palette.background,
				color: theme.palette.color
			}}>
			<Header
				currentTheme={theme}
				colorTheme={colorTheme}
				switchColorTheme={switchColorTheme}
			/>
		</div>
	);
}

export default App;
