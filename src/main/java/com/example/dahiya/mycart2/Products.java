package com.example.dahiya.mycart2;

import android.util.Log;

public class Products  {

    private int _id;
    private String _company;
    private String _model;
    private float _price;
    private float _rating;
    private int _ram;
    private int _storage;
    private float _screen;
    private int _battery;
    private int _front_camera;
    private int _rear_camera;
    private String _type;
    private int _rates;


    private String _ids;
    private String _companys;
    private String _models;
    private String _prices;
    private String _ratings;
    private String _rams;
    private String _storages;
    private String _screens;
    private String _batterys;
    private String _front_cameras;
    private String _rear_cameras;
    private String _types;
    private String _ratess;

    public Products(){

    }

    public Products(String _company, String _model, String _price, String _ram,
                    String _storage, String _screen, String _battery, String _front_camera, String _rear_camera,String  _type) {


        this._companys = _company;
        this._models = _model;
        this._prices = _price;
        this._rams = _ram;
        this._storages = _storage;
        this._screens = _screen;
        this._batterys = _battery;
        this._front_cameras = _front_camera;
        this._rear_cameras = _rear_camera;
        this._types=_type;

        this._company=this._companys;
        this._model=this._models;
        this._price=Float.valueOf(this._prices);
        this._screen=Float.valueOf(this._screens);
        this._ram=Integer.parseInt(this._rams);
        this._battery=Integer.parseInt(this._batterys);
        this._front_camera=Integer.parseInt(this._front_cameras);
        this._rear_camera=Integer.parseInt(this._rear_cameras);
        this._storage=Integer.parseInt(this._storages);
        this._type=this._types;

    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_company() {
        return _company;
    }

    public void set_company(String _company) {
        this._company = _company;
    }

    public String get_model() {
        return _model;
    }

    public void set_model(String _model) {
        this._model = _model;
    }

    public float get_price() {
        return _price;
    }

    public String get_ids() {
        return _ids;
    }

    public void set_ids(String _ids) {
        this._ids = _ids;
    }

    public String get_companys() {
        return _companys;
    }

    public void set_companys(String _companys) {
        this._companys = _companys;
    }

    public String get_models() {
        return _models;
    }

    public void set_models(String _models) {
        this._models = _models;
    }

    public String get_prices() {
        return _prices;
    }

    public void set_prices(String _prices) {
        this._prices = _prices;
    }

    public String get_ratings() {
        return _ratings;
    }

    public void set_ratings(String _ratings) {
        this._ratings = _ratings;
    }

    public String get_rams() {
        return _rams;
    }

    public void set_rams(String _rams) {
        this._rams = _rams;
    }

    public String get_storages() {
        return _storages;
    }

    public void set_storages(String _storages) {
        this._storages = _storages;
    }

    public String get_screens() {
        return _screens;
    }

    public void set_screens(String _screens) {
        this._screens = _screens;
    }

    public String get_batterys() {
        return _batterys;
    }

    public void set_batterys(String _batterys) {
        this._batterys = _batterys;
    }

    public String get_front_cameras() {
        return _front_cameras;
    }

    public void set_front_cameras(String _front_cameras) {
        this._front_cameras = _front_cameras;
    }

    public String get_rear_cameras() {
        return _rear_cameras;
    }

    public void set_rear_cameras(String _rear_cameras) {
        this._rear_cameras = _rear_cameras;
    }

    public String get_types() {
        return _types;
    }

    public void set_types(String _types) {
        this._types = _types;
    }

    public String get_ratess() {
        return _ratess;
    }

    public void set_ratess(String _ratess) {
        this._ratess = _ratess;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public float get_rating() {
        return _rating;
    }

    public void set_rating(float _rating) {
        this._rating = _rating;
    }

    public int get_ram() {
        return _ram;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public int get_rates() {
        return _rates;
    }

    public void set_rates(int _rates) {
        this._rates = _rates;
    }

    public void set_ram(int _ram) {
        this._ram = _ram;
    }

    public int get_storage() {
        return _storage;
    }

    public void set_storage(int _storage) {
        this._storage = _storage;
    }

    public float get_screen() {
        return _screen;
    }

    public void set_screen(float _screen) {
        this._screen = _screen;
    }

    public int get_battery() {
        return _battery;
    }

    public void set_battery(int _battery) {
        this._battery = _battery;
    }

    public int get_front_camera() {
        return _front_camera;
    }

    public void set_front_camera(int _front_camera) {
        this._front_camera = _front_camera;
    }

    public int get_rear_camera() {
        return _rear_camera;
    }

    public void set_rear_camera(int _rear_camera) {
        this._rear_camera = _rear_camera;
    }

}
