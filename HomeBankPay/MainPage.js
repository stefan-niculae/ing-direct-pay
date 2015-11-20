'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image,
  TouchableOpacity,
  ListView,
} = React;

var MainPage = React.createClass({
  getInitialState: function() {
    var ds = new ListView.DataSource({
      rowHasChanged: (r1, r2) => r1 !== r2
    });
    var accounts = [{
        sum: 450,
        account: "3213fdksajhfdks3232",
      },
      {
        sum: 122,
        account: "3213fdksajhfdks3232",
      }];
    return {
      accounts: accounts,
      dataSource: ds.cloneWithRows(accounts)
    }
  },

  _renderHeader: function() {
    return (
      <View style={styles.header}>
        <Image
          style={styles.imageLeft}
          source={require('./img/ing_logo.png')}>
        </Image>
        <View style={{flex: 0.2}}/>
        <Image
          style={styles.imageRight}
          source={require('./img/right_panel.png')}>
        </Image>
      </View>
    );
  },

  _renderItem: function(item) {
    var onPress = function() {
      console.log(item.sum);
      this.props.navigateToQrCodeReading();
    }.bind(this);

    return (
      <View style={styles.rowItem}>
        <View style={styles.leftInfo}>
          <Text style={styles.text1}>
            Cont curent
          </Text>
          <Text style={styles.text2}>
            {item.account}
          </Text>
        </View>
        <View style={styles.rightInfo}>
          <Text style={styles.text3}>
            {item.sum}$
          </Text>
          <TouchableOpacity 
            style={{margin: 5, marginRight: 0}}
            onPress={onPress}>
            <Image
              style={styles.qrImage}
              source={require('./img/qr.png')}>
            </Image>
          </TouchableOpacity>
        </View>
      </View>
    );
  },
// contentContainerStyle={ styles.listContainer }
  render: function() {
    return (
      <View style={styles.body}>
        <ListView
          removeClippedSubviews={true}
          style={styles.listView}
          dataSource={ this.state.dataSource }
          renderHeader={ this._renderHeader }
          renderRow={ this._renderItem } />
      </View>
    );
  },
});

var styles = StyleSheet.create({
  body:{
    flex: 1,
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    top: 0,
  },
  listView: {
    flex: 1,
  },
  listContainer: {
    // flex: 1,
    flexDirection: 'row',
    backgroundColor: "#FFFFFF",
    marginLeft: 4,
    marginRight: 4,
  },
  imageLeft: {
    flex: 0.2,
    resizeMode: 'contain',
    // width: 200,
    height: 50,
  },
  imageRight: {
    flex: 0.4,
    resizeMode: 'contain',
    // width: 200,
    height: 50,
  },
  qrImage: {
    resizeMode: 'contain',
    // width: 200,
    height: 30,
    width: 30,
  },
  
  header: {
    flex: 1,
    flexDirection: 'row',
    height: 50,
    backgroundColor: "#FFFFFF",
    justifyContent: "space-between",
    margin: 5,
  },
  rowItem: {
    flex: 1,
    flexDirection: 'row',
    height: 50,
    backgroundColor: "#FFFFFF",
    justifyContent: "space-between",
    alignItems: 'stretch',
    margin: 5,
  },

  leftInfo: {
    // flex: 1,
    margin: 5,
    flexDirection: 'column',
    justifyContent: 'center',
  },
  rightInfo: {
    // flex: 1,
    margin: 8,
    flexDirection: 'row',
    alignItems: 'center',
  },
  text1: {
    fontSize: 16,
    color: "#545454",
  },
  text2: {
    fontSize: 11,
    color: "#BDBDBD",
  },
  text3: {
    fontSize: 11,
    fontWeight: "bold",
    color: "#545454",
  }
});

module.exports = MainPage;

