import {StyleSheet} from 'react-native';

export interface ThemeColors {
  primary: string;
  text: string;
  background: string;
  cardBackground: string;
  buttonTextColor: string;
}

export const colors: ThemeColors = {
  primary: '#5856D6',
  text: '#000000',
  background: '#F3F3F3',
  cardBackground: '#FFFFFF',
  buttonTextColor: '#FFFFFF',
};

export const globalStyles = StyleSheet.create({
  title: {
    fontSize: 30,
    fontWeight: 'bold',
    color: colors.text,
  },
  subtitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: colors.text,
  },

  mainContainer: {
    flex: 1,
    backgroundColor: colors.background,
  },
  globalMargin: {
    paddingHorizontal: 20,
    flex: 1,
  },

  btnPrimary: {
    backgroundColor: colors.primary,
    borderRadius: 10,
    padding: 10,
    alignItems: 'center',
  },
  btnPrimaryText: {
    color: colors.text,
    fontSize: 16,
  },
});
