import { Link } from 'expo-router';
import { FlatList, View } from 'react-native';
// import Styles from '../../components/styles/bg-fonts';

export default function Details(){

    const global_style = require('./../../../components/styles/bg-fonts');

    const Pool = [{id: 1},{id: 2},{id: 3}]
    return(
        <View style={global_style.container}>
            <FlatList
            data={Pool}
             renderItem={({ item }) => (
                <Link href={`/candidature/${item.id}`}>                                                                                 
                 Candidats
                 </Link>
            )}
            />
        </View>
    )

}