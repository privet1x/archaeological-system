import React, { Component } from "react";
import Select from "react-select";

class MissionForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: "",
            endDate: "",
            startLocation: "",
            endLocation: "",
            intermediateLocations: [],
            includeIntermediateLocations: false,
            type: ""
        };
    }

    handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        this.setState({
            [name]: type === "checkbox" ? checked : value,
        });
    };

    handleMultiInputChange = (name, value) => {
        this.setState({
            [name]: value,
        });
    };


    handleCreateMission = async () => {
        const { selectedFieldTeam, selectedExplorationVehicle } = this.props;
        const {
            startDate,
            endDate,
            startLocation,
            endLocation,
            includeIntermediateLocations,
            intermediateLocations,
            type
        } = this.state;
        const missingFields = [];

        if (includeIntermediateLocations && (intermediateLocations.includes(parseInt(startLocation)) || intermediateLocations.includes(parseInt(endLocation)))) {
            alert("Intermediate locations cannot include start or end location.");
            return
        }

        if (selectedFieldTeam === '' || selectedExplorationVehicle === '') {
            missingFields.push("Field Team or Exploration vehicle");
        }

        if (type === '') {
            missingFields.push("Type");
        }
        if (startDate === '' || endDate === '') {
            missingFields.push("Start Date or End Date");
        }

        if (startLocation === '' || endLocation === '') {
            missingFields.push("Start Location or End Location");
        }

        if (missingFields.length > 0) {
            const missingFieldsMessage = `Fields for ${missingFields.join(', ')} ${
                missingFields.length > 1 ? 'are' : 'is'
            } empty.`;
            alert(missingFieldsMessage);
            return;
        }
        if (new Date(endDate) < new Date(startDate)) {
            alert("End date cannot be earlier than start date");
            this.setState({
                startDate: "",
                endDate: "",
            });
            return;
        }

        if (type.length < 5 || type.length > 50) {
            alert("Type should be greater 5 symbols and less 50");
            this.setState({type: ""});
            return;
        }

        const isConfirmed = window.confirm("Are you sure you want to create the mission?");
        if (isConfirmed) {
            try {
                const response = await fetch("/api/missions/create", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        startDate,
                        endDate,
                        selectedFieldTeam,
                        selectedExplorationVehicle,
                        startLocation,
                        endLocation,
                        includeIntermediateLocations,
                        intermediateLocations,
                        type
                    }),
                });

                if (response.ok) {
                    alert("Mission created!", this.state);
                } else {
                    const error = await response.text();
                    alert(`Failed to create mission: ${error}`);
                }
            } catch (error) {
                alert(error("Error:", error.message));
            }
            finally {
                this.props.onCancel();
            }
        } else {
            this.props.onCancel();
        }
    };

    handleCancel = () => {
        this.props.onCancel();
    };


    render() {
        const { selectedFieldTeam, selectedExplorationVehicle, locations, explorationVehicleName, fieldTeamName } = this.props;
        const {
            startDate = '',
            endDate = '',
            startLocation = '',
            endLocation = '',
            intermediateLocations = [],
            includeIntermediateLocations = false,
            type = ''
        } = this.state;

        const createStyle ={
            width: "30%",
            padding: '0.5rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.5rem',
            backgroundColor: "#02bd03"
        }

        const cancelStyle ={
            width: "30%",
            padding: '0.5rem',
            border: '1px solid #001a33',
            borderRadius: '4px',
            boxSizing: 'border-box',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.5rem',
            backgroundColor: "#ff0000",
            marginLeft: "3rem"
        }

        return (
            <div className="bodyBlock">
                <label htmlFor="selectedExplorationVehicle">Selected Exploration Vehicle is {explorationVehicleName}</label>
                <label htmlFor="selectedFieldTeam">Selected Field Team is {fieldTeamName}</label>
                <br/>
                <label htmlFor="startDate">Start Date</label>
                <input
                    type="date"
                    name="startDate"
                    value={startDate}
                    onChange={this.handleInputChange}
                />

                <label htmlFor="endDate">End Date</label>
                <input
                    type="date"
                    name="endDate"
                    value={endDate}
                    onChange={this.handleInputChange}
                />

                <label htmlFor="type">Mission type</label>
                <input
                    type="text"
                    name="type"
                    value={type}
                    onChange={this.handleInputChange}
                />

                <label htmlFor="startLocation">Start Location</label>
                <select
                    className={"select_location"}
                    id="startLocation"
                    name="startLocation"
                    value={startLocation}
                    onChange={this.handleInputChange}
                >
                    {locations.map((location) => (
                        <option key={location.id} value={location.id}>
                            {`${location.address.city}, ${location.address.country}, ${location.address.street} #${location.address.number}`}
                        </option>
                    ))}
                </select>

                <label htmlFor="endLocation">End Location</label>
                <select
                    className={"select_location"}
                    id="endLocation"
                    name="endLocation"
                    value={endLocation}
                    onChange={this.handleInputChange}>
                    {locations.map((location) => (
                        <option key={location.id} value={location.id}>
                            {`${location.address.city}, ${location.address.country}, ${location.address.street} #${location.address.number}`}
                        </option>
                    ))}
                </select>

                <label htmlFor="includeIntermediateLocations">
                    Include Intermediate Locations
                </label>

                <input
                    type="checkbox"
                    id="includeIntermediateLocations"
                    name="includeIntermediateLocations"
                    checked={includeIntermediateLocations}
                    onChange={this.handleInputChange}
                />
                {includeIntermediateLocations && (
                    <div>
                        <label htmlFor="intermediateLocations">Intermediate Locations:</label>
                        <Select
                            className="select_location"
                            id="intermediateLocations"
                            name="intermediateLocations"
                            isMulti
                            options={locations.map((location) => ({
                                value: location.id,
                                label: `${location.address.city}, ${location.address.country}`,
                            }))}
                            value={intermediateLocations.map((locationId) => ({
                                value: locationId,
                                label: locations.find((location) => location.id === locationId)?.address
                                    ? `${locations.find((location) => location.id === locationId)?.address.city}, ${locations.find((location) => location.id === locationId)?.address.country}`
                                    : "",
                            }))}
                            onChange={(selectedOptions) =>
                                this.handleMultiInputChange(
                                    "intermediateLocations",
                                    selectedOptions.map((option) => option.value)
                                )
                            }
                        />
                    </div>
                )}

                <div className={"buttons"}>
                    <button className={"create"} style={createStyle} onClick={this.handleCreateMission}>Create Mission
                    </button>
                    <button className={"cancel"} style={cancelStyle} onClick={this.handleCancel}>Cancel</button>
                </div>
            </div>
        );
    }
}

export default MissionForm;