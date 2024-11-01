import {StyleSheet} from 'react-native';

export interface ThemeColors {
  primary: string;
  text: string;
  background: string;
  cardBackground: string;
  buttonTextColor: string;
  disabled: string;
  primaryDark: string;
  textHint: string;
  backgroundTransparent?: string;
}

export const colors: ThemeColors = {
  primary: '#5856D6',
  text: '#000000',
  background: '#F3F3F3',
  cardBackground: '#FFFFFF',
  buttonTextColor: '#FFFFFF',
  disabled: '#D9D9D9',
  primaryDark: '#7775e9',
  textHint: '#9E9E9E',
  backgroundTransparent: 'rgba(243,243,243,0.3)',
};

export const lightColors: ThemeColors = {
  primary: '#5856D6',
  text: '#000000',
  background: '#F3F3F3',
  cardBackground: '#FFFFFF',
  buttonTextColor: '#FFFFFF',
  disabled: '#D9D9D9',
  primaryDark: '#7775e9',
  textHint: '#9E9E9E',
  backgroundTransparent: 'rgba(243,243,243,0.3)',
};

export const darkColors: ThemeColors = {
  primary: '#9b5d30',
  text: '#FFFFFF',
  background: '#090909',
  cardBackground: '#2D2D2D',
  buttonTextColor: '#FFFFFF',
  disabled: '#626262',
  primaryDark: '#cb7f48',
  textHint: '#9E9E9E',
  backgroundTransparent: 'rgba(0,0,0,0.9)',
};

export const globalStyles = StyleSheet.create({
  title: {
    fontSize: 30,
    fontWeight: 'bold',
    // color: colors.text,
  },
  subtitle: {
    fontSize: 20,
    fontWeight: 'bold',
    // color: colors.text,
  },

  mainContainer: {
    flex: 1,
    // backgroundColor: colors.background,
  },
  globalMargin: {
    paddingHorizontal: 20,
    flex: 1,
  },

  btnPrimary: {
    // backgroundColor: colors.primary,
    borderRadius: 10,
    padding: 10,
    alignItems: 'center',
  },
  btnPrimaryText: {
    // color: colors.text,
    fontSize: 16,
  },

  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
    borderColor: 'rgba(0,0,0,0.3)',
    borderRadius: 10,
    // color: colors.text,
  },
});
