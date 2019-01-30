import * as React from 'react';
import ListEvent from './listEvent';
import SaveEvent from './saveEvent';
import { Button, CssBaseline, Grid, CircularProgress, Typography } from '@material-ui/core';
import EventService from '../services/eventService';


const style = {
    root: {
        padding: 20
    },
    loadRoot: {
        height: '100vh',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center'
    }
};


class WrapEvent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { show: false, events: [], loading: true };
    }

    async componentDidMount() {
        const data = await EventService.getAllEvents();
        this.setState({ events: data });
        this.setState({ loading: false });
    }

    showModal = () => {
        this.setState({ show: true });
    };

    closeModal = (data) => {        
        if (!data) {
            this.setState({ show: false });
            return;
        }
        const { events } = this.state;
        events.push(data);
        this.setState({ show: false, events: events });
    }

    render() {
        const { loading } = this.state;
        if (loading) {
            return (
                <Grid container style={style.loadRoot}>
                    <CssBaseline />
                    <CircularProgress size={60} />
                    <Typography variant="title">Just a moment...</Typography>
                </Grid>
            );
        }

        return (
            <div>
                <ListEvent events={this.state.events}></ListEvent>
                <Button style={{ marginTop: 10 }} color="primary" variant="contained"
                    onClick={this.showModal} >Add Event</Button>
                <SaveEvent show={this.state.show} close={this.closeModal}></SaveEvent>
            </div>
        )
    }
}

export default WrapEvent