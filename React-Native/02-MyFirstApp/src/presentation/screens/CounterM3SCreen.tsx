import React, {useState} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import {globalStyles} from '../ theme/global.styles';
import {FAB} from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialIcons';

export const CounterM3Screen = () => {
  const [count, setCount] = useState(0);

  return (
    <View style={globalStyles.centerContainer}>
      <Text style={globalStyles.title}> {count} </Text>

      <Icon name="terminal" size={25} />

      <FAB
        icon="add"
        style={styles.fab}
        onPress={() => setCount(count + 1)}
        onLongPress={() => setCount(0)}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  fab: {
    position: 'absolute',
    margin: 16,
    right: 0,
    bottom: Platform.OS === 'android' ? 15 : 0,
  },
});
