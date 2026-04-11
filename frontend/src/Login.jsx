import React, { useState } from 'react';
import axios from 'axios';

// Προσθέσαμε το onSwitchToRegister στα props
function Login({ onLogin, onSwitchToRegister }) {
    const [credentials, setCredentials] = useState({
        name: '',
        password: ''
    });

    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', credentials);
            const token = response.data.token;
            localStorage.setItem('token', token);

            if (onLogin) onLogin();

        } catch (err) {
            setError('Λάθος όνομα χρήστη ή κωδικός πρόσβασης.');
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h2>Είσοδος</h2>
                <form onSubmit={handleSubmit}>
                    <div style={styles.inputGroup}>
                        <label>Όνομα Χρήστη:</label>
                        <input
                            type="text"
                            name="name"
                            value={credentials.name}
                            onChange={handleChange}
                            required
                            style={styles.input}
                        />
                    </div>
                    <div style={styles.inputGroup}>
                        <label>Κωδικός:</label>
                        <input
                            type="password"
                            name="password"
                            value={credentials.password}
                            onChange={handleChange}
                            required
                            style={styles.input}
                        />
                    </div>
                    {error && <p style={styles.error}>{error}</p>}
                    <button type="submit" style={styles.button}>Σύνδεση</button>
                </form>

                <div style={{ marginTop: '20px', borderTop: '1px solid #eee', paddingTop: '15px' }}>
                    <p style={{ fontSize: '0.9rem', color: '#666' }}>Δεν έχετε λογαριασμό;</p>
                    <button
                        onClick={onSwitchToRegister}
                        style={styles.linkButton}
                    >
                        Δημιουργία νέου λογαριασμού
                    </button>
                </div>
            </div>
        </div>
    );
}

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '80vh'
    },
    card: {
        padding: '2rem',
        borderRadius: '12px',
        boxShadow: '0 8px 16px rgba(0,0,0,0.1)',
        backgroundColor: '#fff',
        width: '320px',
        textAlign: 'center'
    },
    inputGroup: {
        marginBottom: '1rem',
        textAlign: 'left'
    },
    input: {
        width: '100%',
        padding: '10px',
        marginTop: '5px',
        borderRadius: '6px',
        border: '1px solid #ddd',
        boxSizing: 'border-box'
    },
    button: {
        width: '100%',
        padding: '12px',
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        borderRadius: '6px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: 'bold'
    },
    linkButton: {
        background: 'none',
        border: 'none',
        color: '#007bff',
        cursor: 'pointer',
        fontSize: '0.95rem',
        textDecoration: 'underline',
        padding: '5px'
    },
    error: {
        color: '#dc3545',
        fontSize: '0.85rem',
        marginBottom: '1rem'
    }
};

export default Login;