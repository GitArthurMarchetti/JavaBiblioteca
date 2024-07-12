const express = require('express');
const app = express();

// Define route for /cadastro
app.get('/cadastro', (req, res) => {
  // Handle the request here, possibly send back a response or render a view
  res.send('Cadastro route');
});

// Other routes and middleware

app.listen(8020, () => {
  console.log('Server is running on port 8020');
});