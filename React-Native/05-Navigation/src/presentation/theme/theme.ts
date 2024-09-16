import {StyleSheet} from 'react-native';

export const globalColors = {
  primary: '#7037EB',
  secondary: '#F72585',
  tertiary: '#3A0CA3',
  success: '#4CC9F0',
  warning: '#FCA311',
  danger: '#E71D36',
  dark: '#22223B',
  background: '#FFFFFF',
};

export const globalStyles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: globalColors.background,
  },

  primaryButton: {
    backgroundColor: globalColors.primary,
    padding: 10,
    borderRadius: 5,
    marginBottom: 10,
    width: '100%',
    alignItems: 'center',
  },

  buttonText: {
    color: globalColors.background,
    fontSize: 18,
  },
});
