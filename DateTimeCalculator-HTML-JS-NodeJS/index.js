import express from 'express';
import cookieParser from 'cookie-parser';
import path  from "path";

const app = express();
app.set('view engine', 'ejs');
app.get('/', function(req, res) {
  res.render('index');
});
app.get('/index.ejs', function(req, res) {
  res.render('index');
});
app.get('/view1.ejs?', function(req, res) {
  res.render('view1');
});
app.get('/view2.ejs?', function(req, res) {
  res.render('view2');
});
app.get('/view3.ejs?', function(req, res) {
  res.render('view3');
});
app.get('/view4.ejs?', function(req, res) {
  res.render('view4');
});
app.get('/view5.ejs?', function(req, res) {
  res.render('view5');
});
app.get('/view6.ejs?', function(req, res) {
  res.render('view6');
});
app.get('/view7.ejs?', function(req, res) {
  res.render('view7');
});
app.post('/testing-api', function(req, res) {
  res.send("true");
})
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

app.listen(8081, () => {
  console.log('ES6 Application Port No 8081!');
});