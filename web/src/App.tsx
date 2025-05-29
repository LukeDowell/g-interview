import {useEffect, useState} from 'react'
import './App.css'

interface GProduct {
  id: string,
  name: string
}

function App() {
  const [products, setProducts] = useState<Array<GProduct>>([])
  const [newProductName, setNewProductName] = useState("")

  useEffect(() => updateProductList(), []);

  const updateProductList = () => {
    fetch("http://localhost:8080/products").then(res => res.json())
      .then(body => {
        setProducts(body)
      })
  }

  const createNewProduct = () => {
    fetch("http://localhost:8080/products", {
      method: "POST",
      body: JSON.stringify({ name: newProductName }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => updateProductList())
  }

  return (
    <>
      <h2>Create New Product</h2>
      <input onChange={(e) => setNewProductName(e.target.value)} value={newProductName}/>
      <button onClick={() => createNewProduct()}>Create </button>
      <br/>

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
