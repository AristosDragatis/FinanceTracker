<<<<<<< HEAD
import React, { useState } from 'react';
import axios from 'axios';

function Register({ onRegisterSuccess, onSwitchToLogin }) {
  const [formData, setFormData] = useState({ 
    name: '', 
    email: '', 
    password: '' 
  });
  
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsError(false);

    try {
      await axios.post('http://localhost:8080/api/auth/register', formData);
      
      setMessage('Επιτυχής εγγραφή! Γίνεται ανακατεύθυνση...');
      
      setTimeout(() => {
        if (onRegisterSuccess) onRegisterSuccess();
      }, 1500);

    } catch (err) {
      setIsError(true);
      if (err.response && err.response.data) {
        setMessage(typeof err.response.data === 'string' ? err.response.data : 'Σφάλμα κατά την εγγραφή.');
      } else {
        setMessage('Κάτι πήγε στραβά. Ελέγξτε τη σύνδεσή σας.');
      }
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.text}>Δημιουργία Λογαριασμού</h2>
        <form onSubmit={handleSubmit}>
          
          <div style={styles.inputGroup}>
            <label>Όνομα Χρήστη:</label>
            <input 
              name="name" 
              value={formData.name}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <div style={styles.inputGroup}>
            <label>Email:</label>
            <input 
              type="email"
              name="email" 
              value={formData.email}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <div style={styles.inputGroup}>
            <label>Κωδικός:</label>
            <input 
              type="password" 
              name="password" 
              value={formData.password}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <button type="submit" style={styles.button}>Εγγραφή</button>
        </form>

        {message && (
          <p style={{ ...styles.message, color: isError ? '#dc3545' : '#28a745' }}>
            {message}
          </p>
        )}

        <div style={{ marginTop: '20px', borderTop: '1px solid #eee', paddingTop: '15px' }}>
          <p style={{ fontSize: '0.9rem', color: '#666' }}>Έχετε ήδη λογαριασμό;</p>
          <button onClick={onSwitchToLogin} style={styles.linkButton}>
            Επιστροφή στη Σύνδεση
          </button>
        </div>

      </div>
    </div>
  );
}

const styles = {
  container: { display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' },
  card: { padding: '2rem', borderRadius: '12px', boxShadow: '0 8px 16px rgba(0,0,0,0.1)', backgroundColor: '#fff', width: '320px', textAlign: 'center' },
  inputGroup: { marginBottom: '1rem', textAlign: 'left' },
  input: { width: '100%', padding: '10px', marginTop: '5px', borderRadius: '6px', border: '1px solid #ddd', boxSizing: 'border-box' },
  button: { width: '100%', padding: '12px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '6px', cursor: 'pointer', fontSize: '1rem', fontWeight: 'bold' },
  linkButton: { background: 'none', border: 'none', color: '#007bff', cursor: 'pointer', fontSize: '0.95rem', textDecoration: 'underline', padding: '5px' },
  message: { marginTop: '15px', fontWeight: 'bold', fontSize: '0.9rem' },
  text: {color: 'black'}

};

=======
import React, { useState } from 'react';
import axios from 'axios';

function Register({ onRegisterSuccess, onSwitchToLogin }) {
  const [formData, setFormData] = useState({ 
    name: '', 
    email: '', 
    password: '' 
  });
  
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsError(false);

    try {
      await axios.post('http://localhost:8080/api/auth/register', formData);
      
      setMessage('Η εγγραφή πέτυχε! Γίνεται ανακατεύθυνση...');
      
      setTimeout(() => {
        if (onRegisterSuccess) onRegisterSuccess();
      }, 1500);

    } catch (err) {
      setIsError(true);
      if (err.response && err.response.data) {
        setMessage(typeof err.response.data === 'string' ? err.response.data : 'Σφάλμα κατά την εγγραφή.');
      } else {
        setMessage('Κάτι πήγε στραβά. Ελέγξτε τη σύνδεσή σας.');
      }
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2>Δημιουργία Λογαριασμού</h2>
        <form onSubmit={handleSubmit}>
          
          <div style={styles.inputGroup}>
            <label>Όνομα Χρήστη:</label>
            <input 
              name="name" 
              value={formData.name}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <div style={styles.inputGroup}>
            <label>Email:</label>
            <input 
              type="email"
              name="email" 
              value={formData.email}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <div style={styles.inputGroup}>
            <label>Κωδικός:</label>
            <input 
              type="password" 
              name="password" 
              value={formData.password}
              onChange={handleChange} 
              style={styles.input} 
              required 
            />
          </div>

          <button type="submit" style={styles.button}>Εγγραφή</button>
        </form>

        {message && (
          <p style={{ ...styles.message, color: isError ? '#dc3545' : '#28a745' }}>
            {message}
          </p>
        )}

        <div style={{ marginTop: '20px', borderTop: '1px solid #eee', paddingTop: '15px' }}>
          <p style={{ fontSize: '0.9rem', color: '#666' }}>Έχετε ήδη λογαριασμό;</p>
          <button onClick={onSwitchToLogin} style={styles.linkButton}>
            Επιστροφή στη Σύνδεση
          </button>
        </div>

      </div>
    </div>
  );
}

const styles = {
  container: { display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' },
  card: { padding: '2rem', borderRadius: '12px', boxShadow: '0 8px 16px rgba(0,0,0,0.1)', backgroundColor: '#fff', width: '320px', textAlign: 'center' },
  inputGroup: { marginBottom: '1rem', textAlign: 'left' },
  input: { width: '100%', padding: '10px', marginTop: '5px', borderRadius: '6px', border: '1px solid #ddd', boxSizing: 'border-box' },
  button: { width: '100%', padding: '12px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '6px', cursor: 'pointer', fontSize: '1rem', fontWeight: 'bold' },
  linkButton: { background: 'none', border: 'none', color: '#007bff', cursor: 'pointer', fontSize: '0.95rem', textDecoration: 'underline', padding: '5px' },
  message: { marginTop: '15px', fontWeight: 'bold', fontSize: '0.9rem' }
};

>>>>>>> 8793044 (.)
export default Register;