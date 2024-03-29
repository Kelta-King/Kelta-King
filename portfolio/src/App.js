import { ThemeProvider } from '@mui/material/styles';
import './App.css';
import themeDark from './themeDark';
import themeLight from './themeLight';
import { Box, Button } from '@mui/material';

function App() {
	return (
		<div className="App">
			<ThemeProvider theme={themeDark}>
				<Box style={{ width: "50%", backgroundColor: "black" }}>
					<Button variant='contained'>Clieck</Button>
				</Box>
			</ThemeProvider>
			<ThemeProvider theme={themeLight}>
				<Button variant='contained'>Clieck</Button>
			</ThemeProvider>
		</div>
	);
}

export default App;
