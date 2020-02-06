import React from 'react';
import './Cafe.css';
import { Button } from 'reactstrap';

class CafeDetail extends React.Component{
    componentDidMount() {
      const { location, history } = this.props;
      if (location.state === undefined) {
        history.push("/");
      }
    }
    render() {
      const { location } = this.props;
      console.log(this.props)
      if (location.state) {
        return (
          <center>
          <div >
              <div className="CafeDetail">
                <div style={{alignItems:"center", padding:"30px"}}>
                <img src={location.state.picture} alt={location.state.name} name={location.state.name}/>
                </div>
                <div>
                  <h1>카페 이름 : {location.state.name}</h1>
                  <p>Cafe Description</p>
                  <p>Cafe Location</p>
                  <p>사장님 한마디</p>
                  <Button color="info">주문하러 가기</Button>{' '}
                </div>
              </div>
              <hr></hr>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
          </div>
          </center>
        );
      } else {
        return null;
      }
    }
}

// const card_style = {
//   opacity: 1,
//   display: 'block',
//   backgroundColor: 'white',
// }

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


export default CafeDetail;