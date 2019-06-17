const functions = require('firebase-functions');
const admin = require('firebase-admin');

const defaultFirebase = admin.initializeApp();

exports.createUserDocument = functions.auth.user().onCreate((user) => {
    const email = user.email;
    const displayName = user.displayName;

    defaultFirebase.firestore().collection('usuarios').doc(user.uid).set({
        name: displayName,
        email,
    })
});

exports.deleteUserDocument = functions.auth.user().onDelete((user) => {
    defaultFirebase.firestore().collection('usuarios').doc(user.uid).delete();
});
