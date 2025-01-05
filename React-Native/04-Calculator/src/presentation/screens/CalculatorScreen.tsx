import React from 'react';
import {View, Text, Pressable} from 'react-native';
import {colors, styles} from '../../config/theme/app-theme';
import {CalculatorButton} from '../components/CalculatorButton';
import {useCalculator} from '../hooks/useCalculator';

export const CalculatorScreen = () => {
  const {
    formule,
    number,
    prevNumber,
    buildNumber,
    clean,
    deleteOperation,
    togglePositiveNegative,
    divideOperation,
    multiplyOperation,
    sumOperation,
    substractOperation,
    calculate,
  } = useCalculator();

  return (
    <View style={styles.calculatorContainer}>
      <View style={{paddingHorizontal: 30, paddingBottom: 20}}>
        <Text style={styles.mainResult} adjustsFontSizeToFit numberOfLines={1}>
          {formule}
        </Text>
        {formule === prevNumber ? (
          <Text
            style={styles.subResult}
            adjustsFontSizeToFit
            numberOfLines={1}></Text>
        ) : (
          <Text style={styles.subResult} adjustsFontSizeToFit numberOfLines={1}>
            {prevNumber}
          </Text>
        )}
      </View>

      <View style={styles.row}>
        <CalculatorButton
          onPress={clean}
          label="C"
          color={colors.lightGray}
          blackText
        />
        <CalculatorButton
          onPress={togglePositiveNegative}
          label="±"
          color={colors.lightGray}
          blackText
        />
        <CalculatorButton
          onPress={deleteOperation}
          label="DEL"
          color={colors.lightGray}
          blackText
        />
        <CalculatorButton
          onPress={divideOperation}
          label="÷"
          color={colors.orange}
        />
      </View>
      <View style={styles.row}>
        <CalculatorButton onPress={() => buildNumber('7')} label="7" />
        <CalculatorButton onPress={() => buildNumber('8')} label="8" />
        <CalculatorButton onPress={() => buildNumber('9')} label="9" />
        <CalculatorButton
          onPress={multiplyOperation}
          label="⨯"
          color={colors.orange}
        />
      </View>

      <View style={styles.row}>
        <CalculatorButton onPress={() => buildNumber('4')} label="4" />
        <CalculatorButton onPress={() => buildNumber('5')} label="5" />
        <CalculatorButton onPress={() => buildNumber('6')} label="6" />
        <CalculatorButton
          onPress={substractOperation}
          label="-"
          color={colors.orange}
        />
      </View>

      <View style={styles.row}>
        <CalculatorButton onPress={() => buildNumber('1')} label="1" />
        <CalculatorButton onPress={() => buildNumber('2')} label="2" />
        <CalculatorButton onPress={() => buildNumber('3')} label="3" />
        <CalculatorButton
          onPress={sumOperation}
          label="+"
          color={colors.orange}
        />
      </View>

      <View style={styles.row}>
        <CalculatorButton
          onPress={() => buildNumber('0')}
          label="0"
          doubleSize
        />
        <CalculatorButton onPress={() => buildNumber('.')} label="." />
        <CalculatorButton onPress={calculate} label="=" color={colors.orange} />
      </View>
    </View>
  );
};
