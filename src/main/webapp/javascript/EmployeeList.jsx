import React, { Component } from 'react';

class EmployeeList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showEmploymentDetails: false,
            empFirstName: '',
            empLastName: '',
            employment: {},
            salary: 0,
            responsibilities: '',
            startDate: '',
            endDate: '',
            fieldTeamName: '',
            fieldTeamRole: '',
        };
    }

    handleViewEmploymentInfo = (id) => {
        const employee = this.props.employees.find((emp) => emp.id === id);
        const today = new Date();
        console.log(employee);
        const currentEmployment = employee?.employments?.find((emp) => {
            const endDate = new Date(emp.endDate);
            return endDate >= today;
        });

        if (currentEmployment) {
            this.setState({
                showEmploymentDetails: true,
                startDate: currentEmployment.startDate,
                endDate: currentEmployment.endDate,
                salary: currentEmployment.salary,
                responsibilities: employee.responsibilities,
                empFirstName: employee.firstName,
                empLastName: employee.lastName,
                fieldTeamName: employee.fieldTeam.name,
                fieldTeamRole: Array.from(employee.fieldTeamRole).join(', '),
            }, () => {
                console.log("State updated: ", this.state);
            });
        } else {
            console.log("No current employment found for today's date.");
        }

        console.log(this.state);
    };

    render() {
        const { employees, fieldTeamName } = this.props;

        const cancelStyle = {
            width: '30%',
            padding: '0.5rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.5rem',
            backgroundColor: '#d70000',
            marginTop: '2rem',
        };

        if (employees.length === 0) {
            return (
                <div className="bodyBlock">
                    <h1>No current missions available.</h1>
                    <button className={'cancel'} style={cancelStyle} onClick={this.props.onCancel}>
                        Back
                    </button>
                </div>
            );
        }

        const viewStyle = {
            width: '100%',
            padding: '1rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            backgroundColor: '#001a33',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1rem',
        };

        return (
            <div className="employeeTable">
                {this.state.showEmploymentDetails ? (
                    <div>
                        <h2>Employment Info for {this.state.empFirstName + ' ' + this.state.empLastName}</h2>
                        <form>
                            <label>
                                Employee Works In Field Team Name
                                <input type="text" value={this.state.fieldTeamName} readOnly />
                            </label>
                            <label>
                                Employee Role
                                <input type="text" value={this.state.fieldTeamRole} readOnly />
                            </label>
                            <label>
                                Contract Start Date
                                <input type="text" value={this.state.startDate} readOnly />
                            </label>
                            <label>
                                Contract End Date
                                <input type="text" value={this.state.endDate} readOnly />
                            </label>
                            <label>
                                Salary (USD)
                                <input type="text" value={this.state.salary} readOnly />
                            </label>
                            <label>
                                Responsibilities
                                <input type="text" value={this.state.responsibilities} readOnly />
                            </label>
                        </form>
                        <button className={'cancel'} style={cancelStyle} onClick={this.props.onCancel}>
                            Back
                        </button>
                    </div>
                ) : (
                    <div>
                        <h2>Field Team Employees of {fieldTeamName}</h2>
                        <table>
                            <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Field Team Role</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {employees.map((emp) => (
                                <tr key={emp.id}>
                                    <td>{emp.firstName}</td>
                                    <td>{emp.lastName}</td>
                                    <td>{Array.from(emp.fieldTeamRole).join(', ')}</td>
                                    <td>
                                        <button
                                            className={'view'}
                                            style={viewStyle}
                                            onClick={() => this.handleViewEmploymentInfo(emp.id)}
                                        >
                                            View Employment
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <button className={'cancel'} style={cancelStyle} onClick={this.props.onCancel}>
                            Back
                        </button>
                    </div>
                )}
            </div>
        );
    }
}

export default EmployeeList;
