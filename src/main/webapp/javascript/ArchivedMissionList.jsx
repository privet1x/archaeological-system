import React, { Component } from "react";

class ArchivedMissionList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showMission: false,
            missionDetails: {}
        };
    }

    handleCancel = () => {
        this.setState({ showMission: false });
    };

    handleViewDetails = async (id) => {
        alert("Button clicked with ID: " + id);
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

    renderMissionDetails() {
        const { explorationVehicle, fieldTeam, startDate, endDate, startLocation, endLocation, type, state } = this.state.missionDetails;
        console.log(this.state.missionDetails);
        return (
            <div className={"missionInfo"} style={
                {
                    backgroundColor: "#f2f2f2",
                    position: "absolute",
                    width: "55rem",
                    height: "28rem",
                    top: "0rem",
                    left: "0rem",
                    textAlign: "center",
                    fontSize: "1rem"
                }
            }>
                <h3>Mission Details</h3>
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
                    <br/>
                    <label>
                        End Date:
                        <input type="text" value={endDate} readOnly/>
                    </label>
                    <label>
                        Exploration Vehicle
                        <input type="text" value={explorationVehicle.name} readOnly/>
                    </label>
                    <label>
                        Field Team
                        <input type="text" value={fieldTeam.name} readOnly/>
                    </label>
                    <label>
                        Start Location
                        <input type="text" value={`${startLocation.address.city}, ${startLocation.address.country}, ${startLocation.address.street} #${startLocation.address.number}`} readOnly/>
                    </label>
                    <label>
                        End Location
                        <input type="text" value={`${endLocation.address.city}, ${endLocation.address.country}, ${endLocation.address.street} #${endLocation.address.number}`} readOnly/>
                    </label>
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
                <h2>Archived Missions</h2>
                { this.state.showMission && this.renderMissionDetails()}
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
            </div>
        );
    }
}

export default ArchivedMissionList;