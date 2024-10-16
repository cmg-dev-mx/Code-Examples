import React from 'react';
import {FullMovie} from '../../../core/entities/movie.entity';
import {Text, View, StyleSheet} from 'react-native';
import {Formatter} from '../../../config/helpers/formatter';

interface Props {
  movie: FullMovie;
}

export const MovieDetails = ({movie}: Props) => {
  return (
    <>
      <View
        style={{
          marginHorizontal: 20,
        }}>
        <View style={{flexDirection: 'row'}}>
          <Text>{movie.rating}</Text>
          <Text style={{marginLeft: 5}}>- {movie.genres.join(', ')}</Text>
        </View>
        <Text style={styles.title}>Historia</Text>
        <Text style={styles.description}>{movie.description}</Text>
        <Text style={styles.title}>Presupuesto</Text>
        <Text style={styles.description}>
          {Formatter.formatCurrency(movie.budget)}
        </Text>
      </View>

      <View style={{marginTop: 10, marginBottom: 100}}>
        <Text style={{...styles.title, marginHorizontal: 20}}>Actores</Text>
      </View>
    </>
  );
};

const styles = StyleSheet.create({
  title: {
    fontSize: 23,
    fontWeight: 'bold',
    marginTop: 10,
  },
  description: {
    fontSize: 16,
  },
});
