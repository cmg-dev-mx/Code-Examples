import React from 'react';
import {SafeAreaView, Text, View} from 'react-native';
import {HelloWorldScreen} from './src/presentation/screens/HelloWorldScreen';
import {Counter} from '../01-react-foundation/src/components/Counter';
import {CounterScreen} from './src/presentation/screens/CounterScreen';
import {PaperProvider} from 'react-native-paper';
import {CounterM3Screen} from './src/presentation/screens/CounterM3SCreen';

import MaterialIcon from 'react-native-vector-icons/MaterialIcons';

export const App = () => {
  return (
    <PaperProvider
      settings={{
        icon: props => <MaterialIcon {...props} />,
      }}>
      <SafeAreaView style={{flex: 1}}>
        {/* <HelloWorldScreen name="Cesar Morales" /> */}
        {/* <CounterScreen /> */}
        <CounterM3Screen />
      </SafeAreaView>
    </PaperProvider>
  );
};
