import React from 'react';
import ReactDOM from 'react-dom';
import {AppBar, Toolbar, Typography} from '@material-ui/core'
import WrapEvent from './components/wrapEvent';

const App = () => ( 
    <div> 
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" color="inherit">
                    Basic Event Management System
                </Typography>
            </Toolbar>            
        </AppBar>
        <WrapEvent></WrapEvent>
    </div> 
);

ReactDOM.render(<App/>, document.querySelector('#root'));