import React, { Component } from 'react'

export default class Order extends Component {
  render() {
    return (
      <div>
        <h1>`( name )` 카페의 주문하기 페이지</h1>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
      </div>
    )
  }
}

const CafeDetailCard = () => {
  return(
    <div style={{display:"inline-flex", padding:"20px", width:'20rem', margin:"10px"}}>
      <div className="card" style={{width: '18rem'}}>
        <img src={"https://lh3.googleusercontent.com/proxy/87qEiRzohTtouYrf6aQH-miwPDMAIJFFvHVl4cqgxR_R7tLgJ6-HAn9WEJOsJ1sC58_Kt0JgKGe5vPlNoYkXPhpLR4cizE-uU1D_5fVId2lt3MG0pGGJQg"} className="card-img-top" alt="..."/>
        <div className="card-body">
          <p className="card-text">흑당 버블티</p>
        </div>
      </div>
    </div>
  );
};