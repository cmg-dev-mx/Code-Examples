import React, {useEffect} from 'react';
import {View, Text, Pressable} from 'react-native';
import {styles} from '../../../config';
import {useCounterStore} from '../../store/counter-store';
import {useNavigation} from '@react-navigation/native';

export const CounterScreen = () => {
  const counter = useCounterStore(state => state.count);
  const increment = useCounterStore(state => state.increment);
  const reset = useCounterStore(state => state.reset);

  const navigation = useNavigation();

  useEffect(() => {
    navigation.setOptions({
      title: `Counter ${counter}`,
    });
  });

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Count: {counter}</Text>

      <Pressable
        style={styles.primaryButton}
        onPress={() => {
          increment();
        }}>
        <Text>Increment counter</Text>
      </Pressable>
      <Pressable
        style={styles.primaryButton}
        onPress={() => {
          reset();
        }}>
        <Text>Reset counter</Text>
      </Pressable>
    </View>
  );
};
