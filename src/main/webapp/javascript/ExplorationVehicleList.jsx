import React, { Component } from "react";

class ExplorationVehicleList extends Component {

    render() {
        const { explorationVehicles, companyName, onSelectExplorationVehicle, selectedExplorationVehicleId, onContinueClick } = this.props;

        if (explorationVehicles.length === 0) {
            alert("No Exploration vehicles for this company.");
            this.props.onCancel();
        }

        const buttonStyle = {
            width: "60%",
            padding: '1rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            margin: '1rem auto',
            backgroundColor: '#001a33',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.7rem',
        };

        const cancelStyle ={
            width: "60%",
            padding: '1rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.5rem',
            backgroundColor: "#ff0000"
        }

        const buttons = {

        }

        return (
            <div className="bodyBlock">
                <label htmlFor="explorationVehicleSelect">Select {companyName}'s ExplorationVehicle</label>
                <select
                    id="explorationVehicleSelect"
                    size="5"
                    style={{width: '66%'}}
                    onChange={(e) => onSelectExplorationVehicle(e.target.value)}
                    value={selectedExplorationVehicleId}
                >
                    {explorationVehicles.map((explorationVehicle) => (
                        <option key={explorationVehicle.id} value={explorationVehicle.id}>
                            {`${explorationVehicle.name}`}
                        </option>
                    ))}
                </select>

                <div style={buttons}>
                    <button style={buttonStyle} onClick={() => onContinueClick()}>
                        Continue
                    </button>
                    <br/>
                    <button className={"cancel"} style={cancelStyle} onClick={this.props.onCancel}>Back</button>
                </div>
            </div>
        );
    }
}

export default ExplorationVehicleList;