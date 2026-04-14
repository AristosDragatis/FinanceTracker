import React from 'react';

function MainMenu({ onNavigate }) {
  return (
    <div style={styles.container}>
      <h2>Καλωσήρθες στο Finance Tracker!</h2>
      <p style={{ color: '#666', marginBottom: '30px' }}>Τι θα ήθελες να κάνεις σήμερα;</p>
      
      <div style={styles.grid}>
        <button 
          style={styles.cardButton} 
          onClick={() => onNavigate('transactions')}
        >
          <h3 style={{ margin: '0 0 10px 0' }}> Οι Συναλλαγές μου</h3>
          <p style={{ margin: '0', fontSize: '0.9rem', color: '#555' }}>Προσθήκη, προβολή και διαχείριση εξόδων</p>
        </button>

        <button style={{ ...styles.cardButton, opacity: 0.5, cursor: 'not-allowed' }}>
          <h3 style={{ margin: '0 0 10px 0' }}> Στατιστικά (Σύντομα)</h3>
          <p style={{ margin: '0', fontSize: '0.9rem', color: '#555' }}>Γραφήματα και ανάλυση εσόδων-εξόδων</p>
        </button>
      </div>
    </div>
  );
}

const styles = {
  container: { textAlign: 'center', padding: '40px 20px' },
  grid: { display: 'flex', gap: '20px', justifyContent: 'center', flexWrap: 'wrap' },
  cardButton: {
    padding: '20px',
    width: '250px',
    backgroundColor: '#fff',
    color: '#333',
    border: '2px solid #007bff',
    borderRadius: '12px',
    cursor: 'pointer',
    textAlign: 'left',
    boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
    transition: 'transform 0.2s'
  }
};

export default MainMenu;