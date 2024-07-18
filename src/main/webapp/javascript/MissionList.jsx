import React, { Component } from "react";
import EmployeeList from "./EmployeeList";
class MissionList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showFieldTeamDetails: false,
            showMission: false,
            explorationVehicleName: '',
            fieldTeamName: '',
            missionDetail: {},
            fieldTeamDetails: {},
            archaeologicalEmployees: []
        };
    }

    handleCancel = () => {
        this.setState({ showMission: false });
    };

    handleViewCancel = () => {
        this.setState({ showFieldTeamDetails: false });
    };

    handleViewDetails = async (id) => {
        try {
            const response = await fetch("/api/missions/find_by_id/" + id, {
                method: "GET",
                headers: {"Content-Type": "application/json"}
            });

            if (response.ok) {
                const missionDetails = await response.json();
                this.setState({ missionDetails });
                this.setState({showMission: true});
                console.log("Mission details:", missionDetails);
            } else {
                const error = await response.text();
                alert("Failed to find mission with ID=" + id.toString() + " : " + error);
            }
        } catch (error) {
            alert(error("Error:", error.message));
        } finally {
        }
    };

    fetchEmployees(id) {
        fetch("/api/fieldTeams/getEmployeesById/" + id)
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        archaeologicalEmployees: response,
                    });
                },
                (error) => {
                    alert(error);
                }
            );
    }

    handleViewFieldTeamDetailsClick = (id) => {
        this.fetchEmployees(id);
        this.setState({showFieldTeamDetails: true});
    };

    handleViewEmployeesCancel = () => {
        this.setState({ showFieldTeamDetails: false });
    };

    renderMissionDetails() {
        const { explorationVehicle, fieldTeam, startDate, endDate, startLocation, endLocation, type, state } = this.state.missionDetails;
        const intermediateLocations = this.state.missionDetails.intermediateLocations;
        console.log(intermediateLocations);

        const styleList = {
            width: "60%"
        };

        const styleOption = {
            fontSize: "1rem"
        };
        return (
            <div className={"missionInfo"} style={
                {
                    backgroundColor: "#f2f2f2",
                    position: "absolute",
                    width: "55rem",
                    height: "28rem",
                    top: "0rem",
                    left: "0rem",
                    textAlign: "center", fontSize: "1rem"
                }
            }>
                {
                    this.state.showFieldTeamDetails && (
                        <EmployeeList
                            employees={this.state.archaeologicalEmployees}
                            onCancel={this.handleViewEmployeesCancel}
                            fieldTeamName={fieldTeam.name}
                        />
                    )
                }
                {
                    !this.state.showFieldTeamDetails && (
                        <div>
                            <h2>Mission Details</h2>
                            <form>
                                <label>
                                    Type:
                                    <input type="text" value={type} readOnly/>
                                </label>
                                <label>
                                    State:
                                    <input type="text" value={state} readOnly/>
                                </label>
                                <br/>
                                <label>
                                    Start Date:
                                    <input type="text" value={startDate} readOnly/>
                                </label>
                                <label>
                                    End Date:
                                    <input type="text" value={endDate} readOnly/>
                                </label>
                                <br/>
                                <label>
                                    Exploration Vehicle
                                    <input type="text" value={explorationVehicle.name} readOnly/>
                                </label>
                                <label>
                                    Field Team
                                    <input type="text" value={fieldTeam.name} readOnly/>
                                </label>
                                <a
                                    style={{
                                        color: '#0029ff',
                                        cursor: 'pointer',
                                        fontSize: '0.8rem',
                                        marginTop: '1rem',
                                        textDecoration: 'underline',
                                    }}
                                    onClick={() => this.handleViewFieldTeamDetailsClick(fieldTeam.id)}
                                >
                                    View Field Team Info
                                </a>
                                <label>
                                    Start Location
                                    <input type="text"
                                           value={`${startLocation.address.city}, ${startLocation.address.country}, ${startLocation.address.street} #${startLocation.address.number}`}
                                           readOnly/>
                                </label>
                                <label>
                                    End Location
                                    <input type="text"
                                           value={`${endLocation.address.city}, ${endLocation.address.country}, ${endLocation.address.street} #${endLocation.address.number}`}
                                           readOnly/>
                                </label>
                                {intermediateLocations.length !== 0 && (
                                    <label>
                                        Intermediate Locations:
                                        {intermediateLocations.map((locationObj, index) => (
                                            <select multiple readOnly style={styleList}>
                                                {intermediateLocations.map((locationObj, index) => (
                                                    <option key={index} value={locationObj.location.id} disabled
                                                            style={styleOption}>
                                                        {`${locationObj.location?.address.city}, ${locationObj.location?.address.country}, ${locationObj.location?.address.street} #${locationObj.location?.address.number}`}
                                                    </option>
                                                ))}
                                            </select>
                                        ))}
                                    </label>
                                )}
                                <br/>
                            </form>
                            <button className={"cancel"} style={{
                                width: "100%",
                                padding: '1rem',
                                border: '1px solid #001a33',
                                borderRadius: '4px',
                                boxSizing: 'border-box',
                                backgroundColor: '#001a33',
                                color: '#ffffff',
                                cursor: 'pointer',
                                fontSize: '1rem'
                            }} onClick={this.handleCancel}>Cancel
                            </button>
                        </div>
                    )
                }
            </div>
        );
    }


    render() {
        const {missions} = this.props;

        if (!missions || missions.length === 0) {
            return (
                <div className="bodyBlock">
                    <h1>No current missions available.</h1>
                </div>
            );
        }

        const cancelStyle ={
            width: "30%",
            padding: '0.5rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            color: '#ffffff',
            margin: "0 17rem",
            cursor: 'pointer',
            fontSize: '1.2rem',
            backgroundColor: "#bd0000"
        }

        const viewStyle = {
            width: "100%",
            padding: '1rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            backgroundColor: '#001a33',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1rem'
        }

        return (
            <div className="tableBlock">
                <h2>Current Missions</h2>
                {this.state.showMission && this.renderMissionDetails()}
                <table>
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>State</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {missions.map((mission) => (
                        <tr key={mission.id}>
                            <td>{mission.type}</td>
                            <td>{mission.startDate}</td>
                            <td>{mission.endDate}</td>
                            <td>{mission.state}</td>
                            <td>
                                <button className={"view"} style={viewStyle}
                                        onClick={() => this.handleViewDetails(mission.id)}>View Details
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <button className={"cancel"} style={cancelStyle} onClick={this.props.onCancel}>Back</button>
            </div>
        );
    }
}

export default MissionList;