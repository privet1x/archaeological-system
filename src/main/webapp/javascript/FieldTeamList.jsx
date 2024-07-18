import React, { Component } from "react";

class FieldTeamList extends Component {

    render() {
        const { fieldTeams, onSelectFieldTeam, selectedFieldTeamId, onContinueClick } = this.props;
        if (!fieldTeams) {
            alert("No Field Teams yet");
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
                <label htmlFor="fieldTeamSelect">Select Available Field Team</label>
                <select id="fieldTeamSelect" size="5" onChange={(e) => onSelectFieldTeam(e.target.value)}
                        value={selectedFieldTeamId}>
                    {fieldTeams.map((fieldTeam) => (
                        <option key={fieldTeam.id} value={fieldTeam.id}>
                            {`${fieldTeam.name}`}
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

export default FieldTeamList;