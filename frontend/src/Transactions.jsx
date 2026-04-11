import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AddTransaction from './AddTransaction'; // 1. Το νέο μας Component!

function Transactions() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchTransactions = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get('http://localhost:8080/api/transactions/my_transactions', {
        headers: { Authorization: `Bearer ${token}` }
      });
      setTransactions(response.data);
    } catch (error) {
      console.error("Σφάλμα κατά τη φόρτωση:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTransactions();
  }, []);

  if (loading) return <p>Φόρτωση συναλλαγών...</p>;

  return (
    <div style={{ padding: '20px', maxWidth: '800px', margin: 'auto' }}>
      <h2>Οι Συναλλαγές μου</h2>
      
      <AddTransaction onTransactionAdded={fetchTransactions} />

      <table border="1" style={{ width: '100%', textAlign: 'left', borderCollapse: 'collapse', marginTop: '20px' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th style={{ padding: '10px' }}>Ποσό</th>
            <th style={{ padding: '10px' }}>Περιγραφή</th>
            <th style={{ padding: '10px' }}>Κατηγορία</th>
            <th style={{ padding: '10px' }}>Ημερομηνία</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map(t => (
            <tr key={t.id}>
              <td style={{ padding: '10px' }}>{t.amount}€</td>
              <td style={{ padding: '10px' }}>{t.description}</td>
              <td style={{ padding: '10px' }}>{t.category ? t.category.name : 'Χωρίς κατηγορία'}</td>
              <td style={{ padding: '10px' }}>{new Date(t.date).toLocaleDateString('el-GR')}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Transactions;