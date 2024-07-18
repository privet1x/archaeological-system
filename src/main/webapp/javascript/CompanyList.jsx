import React, { Component } from "react";
class CompanyList extends Component {

    render() {
        const { companies, onSelectCompany, selectedCompanyId, onContinueClick } = this.props;
        if (companies.length === 0) {
            alert("No Research Companies yet");
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
            fontSize: '1.5rem'
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
                <label htmlFor="companySelect">Select Company</label>
                <select id="companySelect" size="5" onChange={(e) => onSelectCompany(e.target.value)}
                        value={selectedCompanyId}>
                    {companies.map((company) => (
                        <option key={company.id} value={company.id}>
                            {`${company.name}`}
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

export default CompanyList;