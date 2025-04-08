import { initializeApp } from 'firebase/app'
import { getFirestore } from 'firebase/firestore'

// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: 'AIzaSyBYk8HxrAkwa_8XuhLcJpXhjQMj9zyIa44',
  authDomain: 'mscs-webdev-library.firebaseapp.com',
  projectId: 'mscs-webdev-library',
  storageBucket: 'mscs-webdev-library.firebasestorage.app',
  messagingSenderId: '946537386221',
  appId: '1:946537386221:web:099f992fcd96d24c447ce4',
}

// Initialize Firebase
initializeApp(firebaseConfig)
const db = getFirestore()
export default db
