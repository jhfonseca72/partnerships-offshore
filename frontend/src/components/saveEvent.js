import * as React from 'react';
import EventService from '../services/eventService';
import { Dialog, DialogTitle, DialogContent, TextField, DialogActions, Button, Grid, Select, MenuItem, Typography, Input } from '@material-ui/core';
import Event from '../models/event';
import Venue from '../models/venue';
import states from '../models/states';

class SaveEvent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { event: new Event(), venue: new Venue(), states: states };
    }

    saveEvent = async () => {
        const { event, venue } = this.state;
        event.venue = venue;
        const createdEvent = await EventService.createEvent(event);
        this.setState({ event: new Event(), venue: new Venue() });
        this.props.close(createdEvent.data);
    };

    closeModal = () => {
        this.setState({ event: new Event(), venue: new Venue() });
        this.props.close(null);
    }

    handleEventChange = e => {
        const { name, value } = e.target;
        const { event } = this.state;
        event[name] = value;
        this.setState({ event: event });
    };

    handleVenueChange = e => {
        const { name, value } = e.target;
        const { venue } = this.state;
        venue[name] = value;
        this.setState({ venue: venue });
    };

    render() {
        return (
            <div>
                <Dialog open={this.props.show} onClose={this.closeModal}
                    aria-labelledby="form-dialog-title" maxWidth="xl" >
                    <DialogTitle id="form-dialog-title">Add New Event</DialogTitle>
                    <DialogContent>
                        <Typography variant="subheading">
                            Event Information
                        </Typography>
                        <Grid container spacing={16}>
                            <Grid item xs={6}>
                                <TextField name="name" type="text" label="Event Name"
                                    autoFocus margin="dense" fullWidth
                                    value={this.state.event.name}
                                    onChange={this.handleEventChange} />
                            </Grid>
                            <Grid item xs={6}>
                                <TextField name="date" type="datetime-local" label="Event Date"
                                    InputLabelProps={{
                                        shrink: true,
                                    }} margin="dense" fullWidth
                                    value={this.state.event.date}
                                    onChange={this.handleEventChange} />
                            </Grid>
                        </Grid>
                        <br />
                        <Typography variant="subheading">
                            Venue Information
                        </Typography>
                        <Grid container spacing={16}>
                            <Grid item>
                                <TextField name="name" type="text" label="Venue Name"
                                    margin="dense" fullWidth
                                    value={this.state.venue.name}
                                    onChange={this.handleVenueChange} />
                            </Grid>
                            <Grid item>
                                <TextField name="city" type="text" margin="dense"
                                    label="Venue City" fullWidth
                                    value={this.state.venue.city}
                                    onChange={this.handleVenueChange} />
                            </Grid>
                            <Grid item>
                                <Select name="state" margin="dense" fullWidth style={{ marginTop: 20 }}
                                    displayEmpty
                                    value={this.state.venue.state} onChange={this.handleVenueChange}>
                                    <MenuItem value="">
                                        <em>None</em>
                                    </MenuItem>
                                    {this.state.states.map(row => (
                                        <MenuItem value={row.value}>{row.name}</MenuItem>
                                    ))}
                                </Select>
                            </Grid>
                        </Grid>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.closeModal} color="primary">
                            Close
                        </Button>
                        <Button onClick={this.saveEvent} color="primary">
                            Add Event
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        )
    }
}

export default SaveEvent
