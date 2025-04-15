import { initializeApp } from 'firebase/app'
import { getFirestore } from 'firebase/firestore'

// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: 'AIzaSyDQ9_rn0ex-SHtQO6bQhAeoPJ-LCnCp5oA',
  authDomain: 'mscs-webdev-a3.firebaseapp.com',
  projectId: 'mscs-webdev-a3',
  storageBucket: 'mscs-webdev-a3.firebasestorage.app',
  messagingSenderId: '33594389429',
  appId: '1:33594389429:web:b45f9320f539c35d385ffc',
}

export const firebaseApp = initializeApp(firebaseConfig)
const db = getFirestore(firebaseApp)
export default db
