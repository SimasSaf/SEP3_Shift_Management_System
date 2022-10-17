using Microsoft.AspNetCore.Components.Authorization;
using PoCLayer1.Authentication;
using PoCLayer1.Http;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
builder.Services.AddScoped<IUserHttpClient, UserHttpClientImpl>();
builder.Services.AddScoped<IEmployeeHttpClient, EmployeeHttpClientImpl>();
builder.Services.AddScoped<IShiftHttpClient, ShiftHttpClientImpl>();

builder.Services.AddScoped<IAuthService, AuthServiceImpl>();
builder.Services.AddScoped<AuthenticationStateProvider, SimpleAuthStateProvider>();
builder.Services.AddAuthorizationCore();

// Authorization
builder.Services.AddAuthorization(options =>
{
    //options.AddPolicy("LoggedIn", a => a.RequireAuthenticatedUser().RequireClaim("IsLoggedIn", "true"));
   // options.AddPolicy("Manager", policy => policy.RequireClaim("IsManager", "true"));
    options.AddPolicy("Employee", policy => policy.RequireClaim("isEmployee", "true"));
    options.AddPolicy("Manager", policy => policy.RequireClaim("isManager", "true"));
    options.AddPolicy("Admin", policy => policy.RequireClaim("isAdmin", "true"));
});

var app = builder.Build();



// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}



app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.UseAuthentication();

app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

app.Run();