import React, { useState } from 'react';
import Login from './Login';
import Transactions from './Transactions';
import Register from './Register';

function App() {
    // Ελέγχουμε αν ο χρήστης είναι ήδη συνδεδεμένος (αν υπάρχει token)
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

    // State για να ξέρουμε αν θα δείξουμε τη φόρμα Login ή Register
    const [showRegister, setShowRegister] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLoggedIn(false);
    };

    // Αν ο χρήστης είναι συνδεδεμένος, δείχνουμε το Dashboard (Transactions)
    if (isLoggedIn) {
        return (
            <div style={{ fontFamily: 'Arial, sans-serif' }}>
                <nav style={styles.nav}>
                    <strong>Finance Tracker</strong>
                    <button onClick={handleLogout} style={styles.logoutBtn}>Logout</button>
                </nav>
                <div style={{ marginTop: '20px' }}>
                    <Transactions />
                </div>
            </div>
        );
    }

    // Αν ΔΕΝ είναι συνδεδεμένος, δείχνουμε είτε το Login είτε το Register
    return (
        <div style={{ fontFamily: 'Arial, sans-serif' }}>
            <nav style={styles.nav}>
                <strong>Finance Tracker</strong>
            </nav>

            <div style={{ textAlign: 'center', marginTop: '50px' }}>
                {showRegister ? (
                    // Δείξε τη φόρμα Εγγραφής
                    <Register
                        onRegisterSuccess={() => setShowRegister(false)}
                        onSwitchToLogin={() => setShowRegister(false)}
                    />
                ) : (
                    // Δείξε τη φόρμα Σύνδεσης
                    <Login
                        onLogin={() => setIsLoggedIn(true)}
                        onSwitchToRegister={() => setShowRegister(true)}
                    />
                )}
            </div>
        </div>
    );
}

// Μερικά βασικά styles για το Nav
const styles = {
    nav: {
        padding: '10px 20px',
        background: '#333',
        color: 'white',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center'
    },
    logoutBtn: {
        color: 'white',
        background: '#dc3545',
        border: 'none',
        padding: '8px 15px',
        borderRadius: '4px',
        cursor: 'pointer',
        fontWeight: 'bold'
    }
};

export default App;