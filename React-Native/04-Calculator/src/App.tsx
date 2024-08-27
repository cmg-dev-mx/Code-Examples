import React from 'react';
import {StatusBar, View} from 'react-native';
import {CalculatorScreen} from './presentation/screens/CalculatorScreen';

function App() {
  return (
    <View>
      <StatusBar barStyle={'light-content'} backgroundColor={'#444444'} />
      <CalculatorScreen />
    </View>
  );
}

export default App;
