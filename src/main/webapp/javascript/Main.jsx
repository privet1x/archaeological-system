import React, { Component } from "react";
import ReactDOM from 'react-dom';
import '../css/Main.css';
import FieldTeamList from "./FieldTeamList";
import ExplorationVehicleList from "./ExplorationVehicleList";
import MissionList from "./MissionList";
import MissionForm from "./MissionForm";
import ArchivedMissionList from "./ArchivedMissionList";
import CompanyList from "./CompanyList";
class Main extends Component {
    handleSubmit;
    constructor(props) {
        super(props)
        this.state = {
            missions: [],
            locations: [],
            fieldTeams: [],
            explorationVehicles: [],
            createMission: false,
            selectedFieldTeamId: '',
            selectedExplorationVehicleId: '',
            selectedCompanyId: '',
            showFieldTeamList: false,
            showExplorationVehicleList: false,
            showMissionList: false,
            showArchivedMissionList: false,
            showCompanyList: false,
            showMissionForm: false,
            initial: true,
            explorationVehicleName: '',
            companyName: '',
            fieldTeamName: '',
            companies: []
        }
    }

    componentDidMount() {
        this.fetchLocations();
        this.fetchCurrentMissions();
        this.fetchCompanies();
    }

    fetchCompanies() {
        fetch('/api/company')
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        companies: response,
                    });

                    if (response?.length > 0) {
                        this.handleSelectCompany(response[0]?.id.toString());
                    }
                },
                (error) => {
                    alert(error);
                }
            );
    }

    async fetchExplorationVehicleById(vehicleId) {
        try {
            console.log(vehicleId);
            const response = await fetch(`/api/explorationVehicles/byId/${vehicleId}`);

            if (!response.ok) {
                alert('Failed to fetch exploration vehicle');
            }

            const explorationVehicle = await response.json();
            console.log(explorationVehicle);

            this.setState({
                explorationVehicleName: explorationVehicle.name,
            });

            console.log(this.state);
        } catch (error) {
            alert(error.message);
        }
    }

    async fetchCompanyNameById(companyId) {
        try {
            const response = await fetch(`/api/company/byId/${companyId}`);

            if (!response.ok) {
                alert('Failed to fetch company');
            }

            const company = await response.json();
            console.log(company);

            this.setState({
                companyName: company.name,
            });

            console.log(this.state);
        } catch (error) {
            alert(error.message);
        }
    }

    async fetchFieldTeamById(fieldTeamId) {
        try {
            const response = await fetch(`/api/fieldTeams/byId/${fieldTeamId}`);

            if (!response.ok) {
                alert('Failed to fetch Field Team');
            }

            const fieldTeam = await response.json();
            console.log(fieldTeam);

            this.setState({
                fieldTeamName: fieldTeam.name,
            });

            console.log(this.state);
        } catch (error) {
            alert(error.message);
        }
    }

    fetchFieldTeams() {
        fetch("/api/fieldTeams")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        fieldTeams: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    async fetchAvailableFieldTeams() {
        try {
            const response = await fetch('/api/fieldTeams/available');
            if (!response.ok) {
                throw new Error('Failed to fetch available Field Teams');
            }
            const data = await response.json();
            this.setState({
                fieldTeams: data,
            });

            if (data?.length > 0) {
                this.handleSelectFieldTeam(data[0].id.toString());
            }
        } catch (error) {
            alert(error.message);
        }
    }

    fetchExplorationVehiclesByCompanyId(selectedCompanyId) {
        fetch('/api/explorationVehicles/byCompany/' + selectedCompanyId)
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        explorationVehicles: response,
                    });

                    if (response?.length > 0) {
                        this.handleSelectExplorationVehicle(response[0].id.toString());
                    }
                },
                (error) => {
                    alert(error);
                }
            );
    }

    fetchLocations() {
        fetch("/api/locations")
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        locations: response,
                    });
                },
                (error) => {
                    alert(error);
                }
            );
    }

    fetchCurrentMissions() {
        fetch("/api/missions/current")
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        missions: response,
                    });
                },
                (error) => {
                    alert(error);
                }
            );
    }

    fetchArchivedMissions() {
        fetch("/api/missions/archived")
            .then((res) => res.json())
            .then(
                (response) => {
                    this.setState({
                        missions: response,
                    });
                },
                (error) => {
                    alert(error);
                }
            );
    }

    handleCreateMissionClick = () => {
        this.setState({ createMission: true });
        this.setState({ showCompanyList: true });
    };

    handleSelectCompany = (selectedCompanyId) => {
        this.setState({ selectedCompanyId });
        this.fetchExplorationVehiclesByCompanyId(selectedCompanyId);
    };

    handleSelectFieldTeam = (selectedFieldTeamId) => {
        console.log(selectedFieldTeamId);
        this.setState({ selectedFieldTeamId });
    };

    handleSelectExplorationVehicle = (selectedExplorationVehicleId) => {
        this.setState({ selectedExplorationVehicleId });
        console.log(selectedExplorationVehicleId);
    };

    handleContinueClick = (nextComponent) => {
        this.setState({ showCompanyList: false});
        this.fetchCompanyNameById(this.state.selectedCompanyId);
        this.setState({ showExplorationVehicleList: true});
    };

    handleContinueToFieldTeamClick = (nextComponent) => {
        this.fetchAvailableFieldTeams();
        this.setState({ showExplorationVehicleList: false});
        this.setState({ showFieldTeamList: true});
    };

    handleViewMissionsList = (nextComponent) => {
        this.setState({ showMissionList: true});
        this.setState({ initial: false });
        this.fetchCurrentMissions();
    };

    handleViewArchivedMissionsList = (nextComponent) => {
        this.setState({ showArchivedMissionList: true});
        this.setState({ initial: false });
        this.fetchArchivedMissions();
    };

    handleViewMissionsCancel = (nextComponent) => {
        this.setState({ showMissionList: false});
        this.setState({ initial: true });
    };

    handleCreateMissionCancel = () => {
        this.setState({ createMission: false });
        this.setState({ showMissionForm: false });
        this.setState({ initial: true });
    };

    handleSelectCompanyCancel = () => {
        this.setState({ showCompanyList: false });
        this.setState({ createMission: false });
        this.setState({ initial: true });
    };

    handleSelectExplorationVehicleCancel = () => {
        this.setState({ showExplorationVehicleList: false });
        this.setState({ showCompanyList: true });
    };

    handleSelectFieldTeamCancel = () => {
        this.setState({ showFieldTeamList: false });
        this.setState({ showExplorationVehicleList: true });
    };

    generateForm = (nextComponent) => {
        console.log(this.state);

        this.fetchExplorationVehicleById(this.state.selectedExplorationVehicleId);
        this.fetchFieldTeamById(this.state.selectedFieldTeamId);
        this.setState({ showFieldTeamList: false });
        this.setState({ showMissionForm: true });
    };


    render() {
        const headerStyle = {
            height: "5rem",
            backgroundColor: '#001a33',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-between',
            padding: '0 20px',
            borderBottom: '2px solid #fff',
            color: '#ffffff',
            fontFamily: "Arial-BoldMT",
            fontSize: "1.4rem",
            margin: '0'
        };

        const navLinkStyle = {
            margin: '0 10px',
            textDecoration: 'none',
            color: '#ffffff',
            fontSize: '1.3rem',
        };

        const appStyle = {
            height: '100vh',
            display: 'flex',
            flexDirection: 'column',
        };

        const buttonStyle = {
            width: "25rem",
            padding: '1rem',
            border: '1px solid #fff',
            borderRadius: '4px',
            boxSizing: 'border-box',
            margin: '3rem 20rem',
            backgroundColor: '#001a33',
            color: '#ffffff',
            cursor: 'pointer',
            fontSize: '1.7rem',
        };

        return (
            <div style={appStyle}>
                <header style={headerStyle}>
                    <h1>Archaeological System</h1>
                    <nav>
                        <a href="/" style={navLinkStyle}>Home</a>
                    </nav>
                </header>
                <div className="container">
                    <div className="center">
                        {this.state.createMission && this.state.showCompanyList && (
                            <CompanyList
                                companies={this.state.companies}
                                onSelectCompany={this.handleSelectCompany}
                                selectedCompanyId={this.state.selectedCompanyId}
                                onContinueClick={this.handleContinueClick}
                                onCancel={this.handleSelectCompanyCancel}
                            />
                        )}
                        {this.state.createMission && this.state.showExplorationVehicleList && (
                            <ExplorationVehicleList
                                explorationVehicles={this.state.explorationVehicles}
                                onSelectExplorationVehicle={this.handleSelectExplorationVehicle}
                                onCancel={this.handleSelectExplorationVehicleCancel}
                                companyName={this.state.companyName}
                                selectedExplorationVehicleId={this.state.selectedExplorationVehicleId}
                                onContinueClick={this.handleContinueToFieldTeamClick}
                            />
                        )}
                        {this.state.createMission && this.state.showFieldTeamList && (
                            <FieldTeamList
                                fieldTeams={this.state.fieldTeams}
                                onSelectFieldTeam={this.handleSelectFieldTeam}
                                onCancel={this.handleSelectFieldTeamCancel}
                                selectedFieldTeamId={this.state.selectedFieldTeamId}
                                onContinueClick={this.generateForm}
                            />
                        )}
                        {this.state.createMission && this.state.showMissionForm && (
                            <MissionForm
                                selectedFieldTeam={this.state.selectedFieldTeamId}
                                selectedExplorationVehicle={this.state.selectedExplorationVehicleId}
                                explorationVehicleName={this.state.explorationVehicleName}
                                fieldTeamName={this.state.fieldTeamName}
                                onCancel={this.handleCreateMissionCancel}
                                locations={this.state.locations}
                            />
                        )}
                        {this.state.initial && !this.state.createMission && (
                            <button
                                style={buttonStyle}
                                onClick={this.handleCreateMissionClick}
                            >
                                Create Mission
                            </button>
                        )}
                        {this.state.initial && !this.state.createMission && (
                            <button
                                style={buttonStyle}
                                onClick={this.handleViewMissionsList}
                            >
                                Current Missions
                            </button>
                        )}
                        {this.state.initial && !this.state.createMission && (
                            <button
                                style={buttonStyle}
                                onClick={this.handleViewArchivedMissionsList}
                            >
                                Archived Missions
                            </button>
                        )}
                        {
                            this.state.showMissionList && (
                                <MissionList
                                    missions={this.state.missions}
                                    onCancel={this.handleViewMissionsCancel}
                                />
                            )
                        }
                        {
                            this.state.showArchivedMissionList && (
                                <ArchivedMissionList
                                    missions={this.state.missions}
                                />
                            )
                        }
                    </div>
                </div>
            </div>
        );
    }
}

ReactDOM.render(
    <Main/>,
    document.getElementById('react-mountpoint')
);