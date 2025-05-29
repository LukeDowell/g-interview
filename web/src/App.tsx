import {useEffect, useState} from 'react'
import './App.css'

interface GProduct {
  id: string,
  name: string
}

function App() {
  const [products, setProducts] = useState<Array<GProduct>>([])

  useEffect(() => {
    fetch("http://localhost:8080/products").then(res => res.json())
      .then(body => {
        setProducts(body)
      })
  }, []);

  return (
    <>
      <h2>Products</h2>
      <ul>
        {
          products.map(p => <li key={p.id}>{p.name}</li>)
        }
      </ul>
    </>
  )
}

export default App
