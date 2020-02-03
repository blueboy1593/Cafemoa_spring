import React from 'react';

class CafeDetail extends React.Component{
    componentDidMount() {
        const { location, history } = this.props;
        if (location.state === undefined) {
          history.push("/");
        }
      }
      render() {
        const { location } = this.props;
        if (location.state) {

          return (
              <div>
                <img src={location.state.picture} alt={location.state.name} name={location.state.name}/>
                <div>
                  <h1>카페 이름 : {location.state.name}</h1>
                </div>   
              </div>
         
          );
        } else {
          return null;
        }
      }
}

export default CafeDetail;