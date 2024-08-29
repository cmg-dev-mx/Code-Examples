import {useEffect, useRef, useState} from 'react';

enum Operator {
  sum = '+',
  substract = '-',
  multiply = '⨯',
  divide = '÷',
}

export const useCalculator = () => {
  const [formule, setFormule] = useState('');

  const [number, setNumber] = useState('0');
  const [prevNumber, setPrevNumber] = useState('0');

  const lastOperation = useRef<Operator>();

  useEffect(() => {
    if (lastOperation.current) {
      const firstFormulePart = formule.split(' ').at(0);
      setFormule(`${firstFormulePart} ${lastOperation.current} ${number}`);
    } else {
      setFormule(number);
    }
  }, [number]);

  useEffect(() => {
    const subResult = calculateSubResult();
    setPrevNumber(`${subResult}`);
  }, [formule]);

  const buildNumber = (textNumber: string) => {
    if (number.includes('.') && textNumber === '.') return;
    if (number.startsWith('0') || number.startsWith('-0')) {
      if (textNumber === '.') {
        setNumber(number + textNumber);
      } else if (textNumber === '0' && number.includes('.')) {
        setNumber(number + textNumber);
      } else if (textNumber !== '0' && !number.includes('.')) {
        setNumber(textNumber);
      } else if (textNumber === '0' && !number.includes('.')) {
        setNumber(number);
      } else {
        setNumber(number + textNumber);
      }
    } else {
      setNumber(number + textNumber);
    }
  };

  const setLastNumber = () => {
    calculate();

    if (number.endsWith('.')) {
      setPrevNumber(number.slice(0, -1));
    } else {
      setPrevNumber(number);
    }
    setNumber('0');
  };

  const divideOperation = () => {
    setLastNumber();
    lastOperation.current = Operator.divide;
  };

  const multiplyOperation = () => {
    setLastNumber();
    lastOperation.current = Operator.multiply;
  };

  const sumOperation = () => {
    setLastNumber();
    lastOperation.current = Operator.sum;
  };

  const substractOperation = () => {
    setLastNumber();
    lastOperation.current = Operator.substract;
  };

  const calculateSubResult = () => {
    const [firstValue, operation, secondValue] = formule.split(' ');

    const num1 = Number(firstValue);
    const num2 = Number(secondValue);

    if (isNaN(num2)) return num1;

    switch (operation) {
      case Operator.sum:
        return num1 + num2;
      case Operator.substract:
        return num1 - num2;
      case Operator.multiply:
        return num1 * num2;
      case Operator.divide:
        return num1 / num2;
      default:
        throw new Error('Operation not implemented');
    }
  };

  const calculate = () => {
    const result = calculateSubResult();
    setFormule(`${result}`);
    lastOperation.current = undefined;
    setPrevNumber('0');
  };

  const clean = () => {
    setNumber('0');
    setPrevNumber('0');
    lastOperation.current = undefined;
    setFormule('');
  };

  const deleteOperation = () => {
    if (number.length === 1 || (number.length === 2 && number.includes('-'))) {
      setNumber('0');
    } else {
      setNumber(number.slice(0, -1));
    }
  };

  const togglePositiveNegative = () => {
    if (number.includes('-')) {
      setNumber(number.replace('-', ''));
    } else {
      setNumber('-' + number);
    }
  };

  return {
    // Properties
    formule,
    number,
    prevNumber,

    // Methods
    buildNumber,
    clean,
    deleteOperation,
    togglePositiveNegative,
    divideOperation,
    multiplyOperation,
    sumOperation,
    substractOperation,
    calculate,
  };
};
