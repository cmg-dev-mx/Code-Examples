import {SectionList, Text, useWindowDimensions, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {Card} from '../../components/ui/Card';
import {Subtitle} from '../../components/ui/Subtitle';
import {colors} from '../../../config/theme/theme';
import {Separator} from '../../components/ui/Separator';
import {useSafeAreaInsets} from 'react-native-safe-area-context';

export const CustomSectionListScreen = () => {
  const {height} = useWindowDimensions();
  const {top} = useSafeAreaInsets();

  return (
    <CustomView margin>
      <Title text="Lista de personajes" safe />

      <Card>
        <SectionList
          sections={houses}
          keyExtractor={item => item}
          renderItem={({item}) => (
            <Text style={{marginVertical: 2}}>{item}</Text>
          )}
          renderSectionHeader={({section}) => (
            <Subtitle
              text={section.title}
              backgroundColor={colors.cardBackground}
            />
          )}
          stickySectionHeadersEnabled
          showsVerticalScrollIndicator={false}
          SectionSeparatorComponent={Separator}
          ListHeaderComponent={() => <Title text="Personajes" />}
          ListFooterComponent={() => (
            <Title text={`Secciones: ${houses.length}`} />
          )}
          style={{height: height - top - 120}}
        />
      </Card>
    </CustomView>
  );
};

interface Houses {
  title: string;
  data: string[];
}

const houses: Houses[] = [
  {
    title: 'Stark',
    data: ['Arya', 'Sansa', 'Bran', 'Jon', 'Robb', 'Rickon'],
  },
  {
    title: 'Lannister',
    data: [
      'Jaime',
      'Tyrion',
      'Cersei',
      'Tywin',
      'Joffrey',
      'Myrcella',
      'Tommen',
    ],
  },
  {
    title: 'Targaryen',
    data: ['Daenerys', 'Viserys', 'Rhaegar', 'Aegon', 'Jon'],
  },
];
