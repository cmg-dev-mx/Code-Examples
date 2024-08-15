import React from 'react';
import {SafeAreaView} from 'react-native';
import {MainScreen} from './src/presentation/screen/MainScreen';

export const App = () => {
  return (
    <SafeAreaView style={{flex: 1}}>
      <MainScreen />
    </SafeAreaView>
  );
};
