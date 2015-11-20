/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  TouchableHighlight,
} = React;
var BarcodeScanner = require('react-native-barcodescanner');

var ING_ORANGE = '#e66500';
var ING_BLUE = '063e93';

var HomeBankPay = React.createClass({
  getInitialState: function() {
    return {
      currentView: "login"
    }
  },
  navigateToQrCodeReading: function() {
    this.setState({currentView: "readQrCode"});
  },
  login: function() {
    return (
      <TouchableHighlight onPress={this.navigateToQrCodeReading} style={styles.container}>
          <Text style={styles.welcome}>Home Bank with Direct Pay</Text>
      </TouchableHighlight>);
  },
  readQrCode: function() {
    return <ReadQRCode/>;
  },
  render: function() {
    return this[this.state.currentView]();
  },
  _onBarCodeRead(e) {
	  console.log(e);
  }
});

var ReadQRCode = React.createClass({
  getInitialState: function() {
    return {
        torchMode: 'off',
        cameraType: 'back'
    };
  },
  render: function() {
    return (
      <View style={styles.container}>
        <Image
          style={styles.logo}
          source={require("./img/lion.png")}
        />
        <Text style={styles.title}>
          Direct PayING
        </Text>
        <TextInput
          style={styles.nameInput}
          onChangeText={(text) => this.setState({welcomeText: text})}
          placeholder='Name'
          autoCapitalize='words'
          onSubmitEditing={(event) => {
            this.refs.passwordInput.focus();
          }}
          underlineColorAndroid={ING_ORANGE}
        />
        <TextInput
          ref='passwordInput'
          style={styles.passwordInput}
          onChangeText={(text) => this.setState({welcomeText: text})}
          secureTextEntry={true}
          placeholder='Password'
          underlineColorAndroid={ING_ORANGE}
        />
        <TouchableHighlight onPress={this._onPressButton} style={styles.highlight}>
          <Text style={styles.button}>
            Log in
          </Text>
      </TouchableHighlight>
      </View>
    );
  }
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
  },
  logo: {
    resizeMode: 'contain',
    height: 100,
    marginTop: -80,
    // marginBottom: 5,
  },
  title: {
    fontSize: 23,
    textAlign: 'center',
    margin: 35,
    color: ING_BLUE,
  },
  nameInput: {
    height: 40,
    width: 250,
    // textAlign: 'center',
  },
  passwordInput: {
    height: 40,
    width: 250,
  },
  button: {
    backgroundColor: ING_ORANGE,
    color: 'white',
    padding: 1.5,
    width: 125,
    textAlign: 'center',
  },
  highlight: {
    margin: 15,
  }
});

AppRegistry.registerComponent('HomeBankPay', () => HomeBankPay);
