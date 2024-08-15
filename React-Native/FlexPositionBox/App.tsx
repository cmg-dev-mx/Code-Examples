import React from 'react';
import {SafeAreaView} from 'react-native';
import {MainScreen} from './src/presentation/screen/MainScreen';
import {BoxObjectModelScreen} from './src/presentation/screen/BoxObjectModelScreen';
import {DimensionScreen} from './src/presentation/screen/DimensionScreen';

export const App = () => {
  return (
    <SafeAreaView style={{flex: 1}}>
      {/* <MainScreen /> */}
      {/* <BoxObjectModelScreen /> */}
      <DimensionScreen />
    </SafeAreaView>
  );
};
