import {createContext, PropsWithChildren, useState} from 'react';
import {darkColors, lightColors, ThemeColors} from '../../config/theme/theme';

type ThemeColor = 'light' | 'dark';

interface ThemeContextProps {
  theme: ThemeColor;
  colors: ThemeColors;
  isDark: boolean;

  setTheme: (theme: ThemeColor) => void;
}

export const ThemeContext = createContext({} as ThemeContextProps);

export const ThemeProvider = ({children}: PropsWithChildren) => {
  const [currentTheme, setCurrentTheme] = useState<ThemeColor>('light');

  const setTheme = (theme: ThemeColor) => {
    setCurrentTheme(theme);
  };

  return (
    <ThemeContext.Provider
      value={{
        theme: currentTheme,
        colors: currentTheme === 'light' ? lightColors : darkColors,
        isDark: currentTheme === 'dark',
        setTheme: setTheme,
      }}>
      {children}
    </ThemeContext.Provider>
  );
};
