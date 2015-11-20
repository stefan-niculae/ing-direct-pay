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
  TextInput
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
  navigateToPaymentForm: function(qrCode){
    this.setState({currentView: "paymentForm", qrCode: qrCode});
  },
  login: function() {
    return <LoginPage navigateToQrCodeReading={this.navigateToQrCodeReading}/>;
  },
  readQrCode: function() {
    return <ReadQRCode navigateToPaymentForm={this.navigateToPaymentForm}/>;
  },
  paymentForm: function() {
    return <PaymentFormFromQRCode qrCode={this.state.qrCode}/>;
  },
  render: function() {
    return this[this.state.currentView]();
  },
  _onBarCodeRead(e) {
	  console.log(e);
  }
});

var LoginPage = React.createClass({
  propTypes: {
    navigateToQrCodeReading: React.PropTypes.func.isRequired
  },
  _onLogin: function(e) {
    this.props.navigateToQrCodeReading();
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
        <TouchableHighlight onPress={this._onLogin} style={styles.highlight}>
          <Text style={styles.button}>
            Log in
          </Text>
        </TouchableHighlight>
      </View>);
  }
});

var ReadQRCode = React.createClass({
  propTypes: {
    navigateToPaymentForm: React.PropTypes.func.isRequired
  },
  getInitialState: function() {
    return {
        torchMode: 'off',
        cameraType: 'back'
    };
  },
  render: function() {
    return (
      <BarcodeScanner
        onBarCodeRead={this._onBarCodeRead}
        style={{ flex: 1 }}
        torchMode={this.state.torchMode}
        cameraType={this.state.cameraType} />);
  },
  _onBarCodeRead(e) {
    this.props.navigateToPaymentForm(e.data);
  }
});

var PaymentFormFromQRCode = React.createClass({
  propTypes: {
    qrCode: React.PropTypes.string.isRequired
  },
  componentDidMount: function() {
  },
  render: function() {
    return <PaymentForm receiverName="nume"
      iban="iban"
      amount={100}
      details="details"/>;
  }
});

var PaymentForm = React.createClass({
  propTypes: {
    receiverName: React.PropTypes.string.isRequired,
    iban: React.PropTypes.string.isRequired,
    amount: React.PropTypes.number.isRequired,
    details: React.PropTypes.string.isRequired
  },
  getInitialState: function() {
    return {
      receiverName: this.props.receiverName,
      iban: this.props.iban,
      amount: this.props.amount,
      details: this.props.details
    };
  },
  render: function() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Payment Form</Text>
        <View style={styles.formLine}>
          <Text style={styles.formLabel}>Nume</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({receiverName: text})}
            value={this.state.receiverName}/>
        </View>
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
  },
  formLine: {
    flex: 1,
    flexDirection: 'row',
    flexWrap: 'nowrap',
    alignItems: 'flex-start'
  },
  formInput: {fontSize: 20, height: 40, width: 180},
  formLabel: {fontSize: 20, borderColor: 'gray', borderWidth: 1, textAlign: 'center', flex: 1}
});

AppRegistry.registerComponent('HomeBankPay', () => HomeBankPay);
