<<<<<<< HEAD
import React, { useState } from 'react';
import Login from './Login';
import Transactions from './Transactions';
import Register from './Register';
import MainMenu from './MainMenu';

function App() {
    // check if the user is logged in (if there is a token)
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

    // State to show register or login
    const [showRegister, setShowRegister] = useState(false);
    const [currentView, setCurrentView] = useState('menu');

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLoggedIn(false);
    };

    // If the user is logged in show the Dashboard 
    if (isLoggedIn) {
        return (
            <div style={{ fontFamily: 'Arial, sans-serif' }}>
                <nav style={styles.nav}>
                    <strong>Finance Tracker</strong>
                    <button onClick={handleLogout} style={styles.logoutBtn}>Logout</button>
                </nav>
                <div style={{ marginTop: '20px' }}>
                    {currentView === 'menu' && (
                        <MainMenu onNavigate={(view) => setCurrentView(view)} />
                    )}
                    
                    {currentView === 'transactions' && (
                        <Transactions onBack={() => setCurrentView('menu')} />
                    )}
                </div>
            </div>
        );
    }

    // if the user is not logged in (show register or login page)
    return (
        <div style={{ fontFamily: 'Arial, sans-serif' }}>
            <nav style={styles.nav}>
                <strong>Finance Tracker</strong>
            </nav>

            <div style={{ textAlign: 'center', marginTop: '50px' }}>
                {showRegister ? (
                    // Register page
                    <Register
                        onRegisterSuccess={() => setShowRegister(false)}
                        onSwitchToLogin={() => setShowRegister(false)}
                    />
                ) : (
                    // Login page
                    <Login
                        onLogin={() => setIsLoggedIn(true)}
                        onSwitchToRegister={() => setShowRegister(true)}
                    />
                )}
            </div>
        </div>
    );
}

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

=======
import React, { useState } from 'react';
import Login from './Login';
import Transactions from './Transactions';
import Register from './Register';

function App() {
    // check if the user is logged in (if there is a token)
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

    // State to show register or login
    const [showRegister, setShowRegister] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLoggedIn(false);
    };

    // If the user is logged in show the Dashboard 
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

    // if the user is not logged in (show register or login page)
    return (
        <div style={{ fontFamily: 'Arial, sans-serif' }}>
            <nav style={styles.nav}>
                <strong>Finance Tracker</strong>
            </nav>

            <div style={{ textAlign: 'center', marginTop: '50px' }}>
                {showRegister ? (
                    // Register page
                    <Register
                        onRegisterSuccess={() => setShowRegister(false)}
                        onSwitchToLogin={() => setShowRegister(false)}
                    />
                ) : (
                    // Login page
                    <Login
                        onLogin={() => setIsLoggedIn(true)}
                        onSwitchToRegister={() => setShowRegister(true)}
                    />
                )}
            </div>
        </div>
    );
}

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

>>>>>>> 8793044 (.)
export default App;