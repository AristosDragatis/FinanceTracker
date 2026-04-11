import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AddTransaction({ onTransactionAdded }) {
  const [formData, setFormData] = useState({
    categoryName: '',
    amount: '',
    description: ''
  });
  
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const token = localStorage.getItem('token');
        
        const response = await axios.get('http://localhost:8080/api/categories', {
          headers: { Authorization: `Bearer ${token}` }
        });
        
        setCategories(response.data);
      } catch (error) {
        console.error("Σφάλμα κατά τη φόρτωση κατηγοριών:", error);
      }
    };
    fetchCategories();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.categoryName) {
      alert("Παρακαλώ επιλέξτε κατηγορία!");
      return;
    }

    setLoading(true);

    try {
      const token = localStorage.getItem('token');
      await axios.post('http://localhost:8080/api/transactions/save_transaction', formData, {
        headers: { Authorization: `Bearer ${token}` }
      });

      setFormData({ categoryName: '', amount: '', description: '' });
      
      if (onTransactionAdded) onTransactionAdded();
      
    } catch (error) {
      console.error("Σφάλμα:", error);
      alert("Κάτι πήγε στραβά κατά την αποθήκευση.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.container}>
      <h3>Νέα Συναλλαγή</h3>
      <form onSubmit={handleSubmit} style={styles.form}>
        
        <select
          name="categoryName"
          value={formData.categoryName}
          onChange={handleChange}
          required
          style={styles.input}
        >
          <option value="" disabled>Επιλέξτε Κατηγορία</option>
          {categories.map((cat) => (
            <option key={cat.id} value={cat.name}>
              {cat.name}
            </option>
          ))}
        </select>

        <input
          type="number"
          step="0.01"
          name="amount"
          placeholder="Ποσό (€)"
          value={formData.amount}
          onChange={handleChange}
          required
          style={styles.input}
        />
        
        <input
          name="description"
          placeholder="Περιγραφή"
          value={formData.description}
          onChange={handleChange}
          required
          style={styles.input}
        />
        
        <button type="submit" disabled={loading} style={styles.button}>
          {loading ? 'Αποθήκευση...' : 'Προσθήκη'}
        </button>
      </form>
    </div>
  );
}

const styles = {
  container: { marginBottom: '30px', padding: '15px', backgroundColor: '#f9f9f9', borderRadius: '8px', border: '1px solid #ddd' },
  form: { display: 'flex', gap: '10px', alignItems: 'center', flexWrap: 'wrap' },
  input: { padding: '8px', border: '1px solid #ccc', borderRadius: '4px', flex: '1', minWidth: '150px', cursor: 'pointer' },
  button: { padding: '8px 15px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', whiteSpace: 'nowrap' }
};

export default AddTransaction;